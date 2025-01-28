package me.hannsi.lfjg.frame.manager.managers;

import me.hannsi.lfjg.debug.debug.DebugLog;
import me.hannsi.lfjg.frame.Frame;
import me.hannsi.lfjg.frame.manager.Manager;
import me.hannsi.lfjg.frame.setting.system.FrameSettingBase;
import me.hannsi.lfjg.frame.setting.system.ReflectionsLevel;
import me.hannsi.lfjg.utils.math.ANSIColors;
import me.hannsi.lfjg.utils.reflection.ClassUtil;
import me.hannsi.lfjg.utils.reflection.PackagePath;
import me.hannsi.lfjg.utils.time.TimeCalculator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class FrameSettingManager extends Manager {
    private final List<FrameSettingBase<?>> frameSettings;

    public FrameSettingManager(Frame frame) {
        super(frame, "FrameSettingManager");
        this.frameSettings = new ArrayList<>();
        loadFrameSettings();
    }

    public void updateFrameSettings(boolean windowHint) {
        StringBuilder sb = new StringBuilder().append("\n\nFrameSettings Updating...\n\n");
        long tookTime = TimeCalculator.calculate(() -> {
            for (FrameSettingBase<?> frameSettingBase : frameSettings) {
                boolean shouldUpdate = (windowHint && frameSettingBase.isWindowHint()) || (!windowHint && !frameSettingBase.isWindowHint());

                if (shouldUpdate) {
                    frameSettingBase.updateSetting();
                    sb.append("\t[Updated FrameSetting] ").append(frameSettingBase.getName()).append(": ").append(frameSettingBase.getValue()).append("\n");
                }
            }
        });

        sb.append("\n").append(ANSIColors.GREEN + "FrameSettings took ").append(tookTime).append("ms to update!\n");
        DebugLog.debug(getClass(), sb.toString());
    }

    public FrameSettingBase<?> getFrameSetting(Class<? extends FrameSettingBase<?>> frameSettingBase) {
        FrameSettingBase<?> result = null;

        for (FrameSettingBase<?> subType : frameSettings) {
            if (subType.getClass() == frameSettingBase) {
                result = subType;

                break;
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public void loadFrameSettings() {
        Set<Class<? extends FrameSettingBase<?>>> subTypes = (Set<Class<? extends FrameSettingBase<?>>>) (Set<?>) ClassUtil.getClassesFormPackage(PackagePath.frameSettings, FrameSettingBase.class);
        List<Class<? extends FrameSettingBase<?>>> sortedClasses = new ArrayList<>(subTypes);

        sortedClasses.sort(Comparator.comparingInt(clazz -> {
            ReflectionsLevel reflectionsLevel = clazz.getAnnotation(ReflectionsLevel.class);
            return reflectionsLevel != null ? reflectionsLevel.level() : Integer.MAX_VALUE;
        }));

        StringBuilder sb = new StringBuilder().append("\n\nFrameSettings loading...\n");
        long tookTime = TimeCalculator.calculate(() -> {
            int count = 0;
            for (Class<? extends FrameSettingBase<?>> subType : sortedClasses) {
                FrameSettingBase<?> frameSettingBase = ClassUtil.createInstance(getFrame(), subType, getFrame());

                if (frameSettingBase != null) {
                    register(frameSettingBase);
                }

                //noinspection DataFlowIssue
                sb.append("\n\t").append(count).append(".\t").append("Loaded FrameSetting: ").append(frameSettingBase.getName());

                count++;
            }

        });

        sb.append("\n\n").append(ANSIColors.GREEN).append("FrameSettings took ").append(tookTime).append("ms to load!").append("\n");

        DebugLog.debug(getClass(), sb.toString());
    }

    public void register(FrameSettingBase<?> frameSetting) {
        frameSettings.add(frameSetting);
    }

    public List<FrameSettingBase<?>> getFrameSettings() {
        return frameSettings;
    }
}
