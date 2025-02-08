package me.hannsi.lfjg.utils.graphics.image;

import me.hannsi.lfjg.debug.debug.DebugLog;
import me.hannsi.lfjg.debug.exceptions.texture.CreatingTextureException;
import me.hannsi.lfjg.utils.buffer.BufferUtil;
import me.hannsi.lfjg.utils.reflection.ResourcesLocation;
import me.hannsi.lfjg.utils.type.types.TextureLoaderType;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

/**
 * Class for loading textures from various sources.
 */
public class TextureLoader {
    private final ResourcesLocation texturePath;
    private final TextureLoaderType textureLoaderType;
    private int textureId;

    /**
     * Constructs a TextureLoader instance with the specified texture path and loader type.
     *
     * @param texturePath the path to the texture resource
     * @param textureLoaderType the type of texture loader to use
     */
    public TextureLoader(ResourcesLocation texturePath, TextureLoaderType textureLoaderType) {
        this.textureLoaderType = textureLoaderType;
        this.texturePath = texturePath;

        loadTexture();
    }

    /**
     * Loads an image using STBImage and returns it as a ByteBuffer.
     *
     * @param resourcesLocation the location of the resource
     * @param widthBuffer the buffer to store the image width
     * @param heightBuffer the buffer to store the image height
     * @param channelsBuffer the buffer to store the number of channels
     * @return the ByteBuffer containing the image data
     */
    public static ByteBuffer loadImageInSTBImage(ResourcesLocation resourcesLocation, IntBuffer widthBuffer, IntBuffer heightBuffer, IntBuffer channelsBuffer) {
        ByteBuffer image;
        ByteBuffer buffer = null;

        try (InputStream inputStream = resourcesLocation.getInputStream()) {
            if (inputStream == null) {
                throw new RuntimeException("Resource not found: " + resourcesLocation.getPath());
            }

            byte[] data = inputStream.readAllBytes();
            buffer = MemoryUtil.memAlloc(data.length);
            buffer.put(data);
            buffer.flip();

            STBImage.stbi_set_flip_vertically_on_load(true);
            image = STBImage.stbi_load_from_memory(buffer, widthBuffer, heightBuffer, channelsBuffer, STBImage.STBI_rgb_alpha);

            if (image == null) {
                throw new RuntimeException("Failed to load image: " + STBImage.stbi_failure_reason());
            }

            if (widthBuffer.get(0) == 0 || heightBuffer.get(0) == 0) {
                STBImage.stbi_image_free(image);
                throw new RuntimeException("Invalid texture dimensions.");
            }

            return image;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read resource: " + resourcesLocation.getPath(), e);
        } finally {
            if (buffer != null) {
                MemoryUtil.memFree(buffer);
            }
        }
    }

    /**
     * Loads the texture based on the specified loader type.
     */
    private void loadTexture() {
        switch (textureLoaderType) {
            case STBImage -> {
                try (MemoryStack stack = MemoryStack.stackPush()) {
                    IntBuffer width = stack.mallocInt(1);
                    IntBuffer height = stack.mallocInt(1);
                    IntBuffer channels = stack.mallocInt(1);

                    ByteBuffer image = STBImage.stbi_load(texturePath.getPath(), width, height, channels, 4);
                    if (image == null) {
                        throw new RuntimeException("Failed to load icon image: " + texturePath.getPath());
                    }

                    generateTexture(width.get(0), height.get(0), image);

                    STBImage.stbi_image_free(image);
                }
            }
            case JavaCV -> {
                Mat bgrMat = opencv_imgcodecs.imdecode(new Mat(this.texturePath.getBytes()), opencv_imgcodecs.IMREAD_COLOR);

                if (bgrMat.empty()) {
                    DebugLog.error(getClass(), "Image file [" + texturePath + "] not loaded.");
                    return;
                }

                Mat mat = new Mat();
                cvtColor(bgrMat, mat, opencv_imgproc.COLOR_BGR2RGBA);

                generateTexture(mat.cols(), mat.rows(), BufferUtil.matToByteBufferRGBA(mat));
            }
            default -> throw new IllegalStateException("Unexpected value: " + textureLoaderType);
        }
    }

    /**
     * Generates an OpenGL texture from the given image data.
     *
     * @param width the width of the image
     * @param height the height of the image
     * @param buf the ByteBuffer containing the image data
     */
    private void generateTexture(int width, int height, ByteBuffer buf) {
        textureId = glGenTextures();
        if (textureId == 0) {
            throw new CreatingTextureException("Could not create texture");
        }

        glBindTexture(GL_TEXTURE_2D, textureId);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glGenerateMipmap(GL_TEXTURE_2D);

        glBindTexture(GL_TEXTURE_2D, 0);
    }

    /**
     * Binds the texture for rendering.
     */
    public void bind() {
        glBindTexture(GL_TEXTURE_2D, textureId);
    }

    /**
     * Unbinds the texture.
     */
    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    /**
     * Cleans up the texture resources.
     */
    public void cleanup() {
        glDeleteTextures(textureId);
    }

    /**
     * Gets the texture path.
     *
     * @return the texture path
     */
    public ResourcesLocation getTexturePath() {
        return texturePath;
    }

    /**
     * Gets the texture loader type.
     *
     * @return the texture loader type
     */
    public TextureLoaderType getTextureLoaderType() {
        return textureLoaderType;
    }

    /**
     * Gets the texture ID.
     *
     * @return the texture ID
     */
    public int getTextureId() {
        return textureId;
    }
}