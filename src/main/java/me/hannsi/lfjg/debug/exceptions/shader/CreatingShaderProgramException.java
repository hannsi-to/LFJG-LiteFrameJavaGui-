package me.hannsi.lfjg.debug.exceptions.shader;

/**
 * Exception thrown when there is an issue creating the shader program.
 */
public class CreatingShaderProgramException extends RuntimeException {

    /**
     * Constructs a new CreatingShaderProgramException with the specified detail message.
     *
     * @param message the detail message
     */
    public CreatingShaderProgramException(String message) {
        super(message);
        System.err.println(message);
        System.exit(1);
    }
}