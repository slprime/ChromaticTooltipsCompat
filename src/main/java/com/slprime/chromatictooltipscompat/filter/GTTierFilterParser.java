package com.slprime.chromatictooltipscompat.filter;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import net.minecraft.item.ItemStack;

import com.slprime.chromatictooltips.api.TooltipTarget;

import bartworks.util.BWUtil;
import gregtech.api.GregTechAPI;
import gregtech.api.enums.ItemList;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.items.MetaGeneratedItem;
import gregtech.api.items.MetaGeneratedTool;
import gregtech.api.metatileentity.implementations.MTECable;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.ItemMachines;
import gregtech.common.tileentities.machines.multi.MTEDrillerBase;
import gtPlusPlus.xmod.gregtech.api.enums.GregtechItemList;

public class GTTierFilterParser implements Function<String, Predicate<TooltipTarget>> {

    protected static final List<String> tiers = Arrays.asList(
        "ULV", // 0
        "LV", // 1
        "MV", // 2
        "HV", // 3
        "EV", // 4
        "IV", // 5
        "LUV", // 6
        "ZPM", // 7
        "UV", // 8
        "UHV", // 9
        "UEV", // 10
        "UIV", // 11
        "UMV", // 12
        "UXV", // 13
        "MAX", // 14
        "MAX+" // 15
    );

    protected Method drillerBaseTierMethod;

    public GTTierFilterParser() {
        try {
            drillerBaseTierMethod = MTEDrillerBase.class.getDeclaredMethod("getMinTier");
            drillerBaseTierMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            drillerBaseTierMethod = null;
        }
    }

    @Override
    public Predicate<TooltipTarget> apply(String tierStr) {
        final int tier = tiers.indexOf(tierStr.toUpperCase());

        if (tier == -1) {
            return target -> false;
        }

        return target -> {
            final ItemStack stack = target.getItem();

            if (stack == null) {
                return false;
            }

            if (stack.getItem() instanceof ItemMachines) {
                final IMetaTileEntity meta = ItemMachines.getMetaTileEntity(stack);

                if (meta instanceof MTECable handCable) {
                    return GTUtility.getTier(handCable.mVoltage) == tier;
                } else if (meta instanceof MTEDrillerBase drillerBase && drillerBaseTierMethod != null) {
                    int drillerBaseTier = -1;

                    try {
                        drillerBaseTier = (int) drillerBaseTierMethod.invoke(drillerBase);
                    } catch (Exception e) {}

                    return drillerBaseTier == tier;
                }

                return getItemMachinesTier(stack) == tier;
            } else if (stack.getItem() instanceof MetaGeneratedTool tool) {
                final Long[] stats = tool.getElectricStats(stack);

                if (stats != null) {
                    return stats[2] == tier;
                }
            } else if (BWUtil.getCircuitTierFromItemStack(stack) == tier) {
                return true;
            } else {

                if (stack.getItem() instanceof MetaGeneratedItem mgItem) {
                    final Long[] stats = mgItem.getElectricStats(stack);

                    if (stats != null) {
                        return stats[2] == tier;
                    }
                }

                if (areItemStackEqual(stack, getElectricMotor(tier))) {
                    return true;
                }

                if (areItemStackEqual(stack, getFluidRegulator(tier))) {
                    return true;
                }

                if (areItemStackEqual(stack, getElectricPiston(tier))) {
                    return true;
                }

                if (areItemStackEqual(stack, getElectricPump(tier))) {
                    return true;
                }

                if (areItemStackEqual(stack, getRobotArm(tier))) {
                    return true;
                }

                if (areItemStackEqual(stack, getConveyor(tier))) {
                    return true;
                }

                if (areItemStackEqual(stack, getEmitter(tier))) {
                    return true;
                }

                if (areItemStackEqual(stack, getSensor(tier))) {
                    return true;
                }

                if (areItemStackEqual(stack, getFieldGenerator(tier))) {
                    return true;
                }

                if (areItemStackEqual(stack, getEnergyCore(tier))) {
                    return true;
                }

                if (areItemStackEqual(stack, getTieredGTPPMachineCasing(tier))) {
                    return true;
                }

                if (areItemStackEqual(stack, getTransmissionComponent(tier))) {
                    return true;
                }

                if (areItemStackEqual(stack, getTieredMachineHull(tier))) {
                    return true;
                }

                if (areItemStackEqual(stack, getTieredMachineCasing(tier))) {
                    return true;
                }

                if (tier == 1 && areItemStackEqual(stack, ItemList.Battery_Hull_LV.get(1))) {
                    return true;
                }

                if (tier == 2 && areItemStackEqual(stack, ItemList.Battery_Hull_MV.get(1))) {
                    return true;
                }

                if (tier == 3 && areItemStackEqual(stack, ItemList.Battery_Hull_HV.get(1))) {
                    return true;
                }
            }

            return false;
        };

    }

    protected ItemStack getElectricMotor(int tier) {
        return switch (tier) {
            case 0 -> ItemList.Hull_ULV.get(1);
            case 1 -> ItemList.Hull_LV.get(1);
            case 2 -> ItemList.Hull_MV.get(1);
            case 3 -> ItemList.Hull_HV.get(1);
            case 4 -> ItemList.Hull_EV.get(1);
            case 5 -> ItemList.Hull_IV.get(1);
            case 6 -> ItemList.Hull_LuV.get(1);
            case 7 -> ItemList.Hull_ZPM.get(1);
            case 8 -> ItemList.Hull_UV.get(1);
            default -> ItemList.Hull_MAX.get(1);
        };
    }

    protected ItemStack getFluidRegulator(int tier) {
        return switch (tier) {
            case 0 -> ItemList.FluidRegulator_LV.get(1);
            case 1 -> ItemList.FluidRegulator_LV.get(1);
            case 2 -> ItemList.FluidRegulator_MV.get(1);
            case 3 -> ItemList.FluidRegulator_HV.get(1);
            case 4 -> ItemList.FluidRegulator_EV.get(1);
            case 5 -> ItemList.FluidRegulator_IV.get(1);
            case 6 -> ItemList.FluidRegulator_LuV.get(1);
            case 7 -> ItemList.FluidRegulator_ZPM.get(1);
            case 8 -> ItemList.FluidRegulator_UV.get(1);
            default -> ItemList.FluidRegulator_UV.get(1);
        };
    }

    protected ItemStack getElectricPiston(int tier) {
        return switch (tier) {
            case 1 -> ItemList.Electric_Piston_LV.get(1);
            case 2 -> ItemList.Electric_Piston_MV.get(1);
            case 3 -> ItemList.Electric_Piston_HV.get(1);
            case 4 -> ItemList.Electric_Piston_EV.get(1);
            case 5 -> ItemList.Electric_Piston_IV.get(1);
            case 6 -> ItemList.Electric_Piston_LuV.get(1);
            case 7 -> ItemList.Electric_Piston_ZPM.get(1);
            case 8 -> ItemList.Electric_Piston_UV.get(1);
            default -> ItemList.Electric_Piston_UHV.get(1);
        };
    }

    protected ItemStack getElectricPump(int tier) {
        return switch (tier) {
            case 1 -> ItemList.Electric_Pump_LV.get(1);
            case 2 -> ItemList.Electric_Pump_MV.get(1);
            case 3 -> ItemList.Electric_Pump_HV.get(1);
            case 4 -> ItemList.Electric_Pump_EV.get(1);
            case 5 -> ItemList.Electric_Pump_IV.get(1);
            case 6 -> ItemList.Electric_Pump_LuV.get(1);
            case 7 -> ItemList.Electric_Pump_ZPM.get(1);
            case 8 -> ItemList.Electric_Pump_UV.get(1);
            default -> ItemList.Electric_Pump_UHV.get(1);
        };
    }

    protected ItemStack getRobotArm(int tier) {
        return switch (tier) {
            case 1 -> ItemList.Robot_Arm_LV.get(1);
            case 2 -> ItemList.Robot_Arm_MV.get(1);
            case 3 -> ItemList.Robot_Arm_HV.get(1);
            case 4 -> ItemList.Robot_Arm_EV.get(1);
            case 5 -> ItemList.Robot_Arm_IV.get(1);
            case 6 -> ItemList.Robot_Arm_LuV.get(1);
            case 7 -> ItemList.Robot_Arm_ZPM.get(1);
            case 8 -> ItemList.Robot_Arm_UV.get(1);
            default -> ItemList.Robot_Arm_UHV.get(1);
        };
    }

    protected ItemStack getConveyor(int tier) {
        return switch (tier) {
            case 1 -> ItemList.Conveyor_Module_LV.get(1);
            case 2 -> ItemList.Conveyor_Module_MV.get(1);
            case 3 -> ItemList.Conveyor_Module_HV.get(1);
            case 4 -> ItemList.Conveyor_Module_EV.get(1);
            case 5 -> ItemList.Conveyor_Module_IV.get(1);
            case 6 -> ItemList.Conveyor_Module_LuV.get(1);
            case 7 -> ItemList.Conveyor_Module_ZPM.get(1);
            case 8 -> ItemList.Conveyor_Module_UV.get(1);
            default -> ItemList.Conveyor_Module_UHV.get(1);
        };
    }

    protected ItemStack getEmitter(int tier) {
        return switch (tier) {
            case 1 -> ItemList.Emitter_LV.get(1);
            case 2 -> ItemList.Emitter_MV.get(1);
            case 3 -> ItemList.Emitter_HV.get(1);
            case 4 -> ItemList.Emitter_EV.get(1);
            case 5 -> ItemList.Emitter_IV.get(1);
            case 6 -> ItemList.Emitter_LuV.get(1);
            case 7 -> ItemList.Emitter_ZPM.get(1);
            case 8 -> ItemList.Emitter_UV.get(1);
            default -> ItemList.Emitter_UHV.get(1);
        };
    }

    protected ItemStack getSensor(int tier) {
        return switch (tier) {
            case 1 -> ItemList.Sensor_LV.get(1);
            case 2 -> ItemList.Sensor_MV.get(1);
            case 3 -> ItemList.Sensor_HV.get(1);
            case 4 -> ItemList.Sensor_EV.get(1);
            case 5 -> ItemList.Sensor_IV.get(1);
            case 6 -> ItemList.Sensor_LuV.get(1);
            case 7 -> ItemList.Sensor_ZPM.get(1);
            case 8 -> ItemList.Sensor_UV.get(1);
            default -> ItemList.Sensor_UHV.get(1);
        };
    }

    protected ItemStack getFieldGenerator(int tier) {
        return switch (tier) {
            case 1 -> ItemList.Field_Generator_LV.get(1);
            case 2 -> ItemList.Field_Generator_MV.get(1);
            case 3 -> ItemList.Field_Generator_HV.get(1);
            case 4 -> ItemList.Field_Generator_EV.get(1);
            case 5 -> ItemList.Field_Generator_IV.get(1);
            case 6 -> ItemList.Field_Generator_LuV.get(1);
            case 7 -> ItemList.Field_Generator_ZPM.get(1);
            case 8 -> ItemList.Field_Generator_UV.get(1);
            default -> ItemList.Field_Generator_UHV.get(1);
        };
    }

    protected ItemStack getEnergyCore(int tier) {
        return switch (tier) {
            case 0 -> GregtechItemList.Energy_Core_ULV.get(1);
            case 1 -> GregtechItemList.Energy_Core_LV.get(1);
            case 2 -> GregtechItemList.Energy_Core_MV.get(1);
            case 3 -> GregtechItemList.Energy_Core_HV.get(1);
            case 4 -> GregtechItemList.Energy_Core_EV.get(1);
            case 5 -> GregtechItemList.Energy_Core_IV.get(1);
            case 6 -> GregtechItemList.Energy_Core_LuV.get(1);
            case 7 -> GregtechItemList.Energy_Core_ZPM.get(1);
            case 8 -> GregtechItemList.Energy_Core_UV.get(1);
            default -> GregtechItemList.Energy_Core_UHV.get(1);
        };
    }

    protected ItemStack getTieredGTPPMachineCasing(int tier) {
        return switch (tier) {
            case 0 -> GregtechItemList.GTPP_Casing_ULV.get(1);
            case 1 -> GregtechItemList.GTPP_Casing_LV.get(1);
            case 2 -> GregtechItemList.GTPP_Casing_MV.get(1);
            case 3 -> GregtechItemList.GTPP_Casing_HV.get(1);
            case 4 -> GregtechItemList.GTPP_Casing_EV.get(1);
            case 5 -> GregtechItemList.GTPP_Casing_IV.get(1);
            case 6 -> GregtechItemList.GTPP_Casing_LuV.get(1);
            case 7 -> GregtechItemList.GTPP_Casing_ZPM.get(1);
            case 8 -> GregtechItemList.GTPP_Casing_UV.get(1);
            default -> GregtechItemList.GTPP_Casing_UHV.get(1);
        };
    }

    protected ItemStack getTransmissionComponent(int tier) {
        return switch (tier) {
            case 1 -> GregtechItemList.TransmissionComponent_LV.get(1);
            case 2 -> GregtechItemList.TransmissionComponent_MV.get(1);
            case 3 -> GregtechItemList.TransmissionComponent_HV.get(1);
            case 4 -> GregtechItemList.TransmissionComponent_EV.get(1);
            case 5 -> GregtechItemList.TransmissionComponent_IV.get(1);
            case 6 -> GregtechItemList.TransmissionComponent_LuV.get(1);
            case 7 -> GregtechItemList.TransmissionComponent_ZPM.get(1);
            case 8 -> GregtechItemList.TransmissionComponent_UV.get(1);
            default -> GregtechItemList.TransmissionComponent_UHV.get(1);
        };
    }

    protected ItemStack getTieredMachineHull(int tier) {
        return switch (tier) {
            case 0 -> ItemList.Hull_ULV.get(1);
            case 1 -> ItemList.Hull_LV.get(1);
            case 2 -> ItemList.Hull_MV.get(1);
            case 3 -> ItemList.Hull_HV.get(1);
            case 4 -> ItemList.Hull_EV.get(1);
            case 5 -> ItemList.Hull_IV.get(1);
            case 6 -> ItemList.Hull_LuV.get(1);
            case 7 -> ItemList.Hull_ZPM.get(1);
            case 8 -> ItemList.Hull_UV.get(1);
            default -> ItemList.Hull_MAX.get(1);
        };
    }

    protected ItemStack getTieredMachineCasing(int tier) {
        return switch (tier) {
            case 0 -> ItemList.Casing_ULV.get(1);
            case 1 -> ItemList.Casing_LV.get(1);
            case 2 -> ItemList.Casing_MV.get(1);
            case 3 -> ItemList.Casing_HV.get(1);
            case 4 -> ItemList.Casing_EV.get(1);
            case 5 -> ItemList.Casing_IV.get(1);
            case 6 -> ItemList.Casing_LuV.get(1);
            case 7 -> ItemList.Casing_ZPM.get(1);
            case 8 -> ItemList.Casing_UV.get(1);
            default -> ItemList.Casing_MAX.get(1);
        };
    }

    protected int getItemMachinesTier(ItemStack stack) {
        final int tDamage = stack.getItemDamage();

        if (tDamage <= 0 || tDamage >= GregTechAPI.METATILEENTITIES.length) {
            return -1;
        }

        if (GregTechAPI.METATILEENTITIES[tDamage] != null) {
            final IGregTechTileEntity tTileEntity = GregTechAPI.METATILEENTITIES[tDamage].getBaseMetaTileEntity();

            if (tTileEntity.getEUCapacity() > 0L) {

                if (tTileEntity.getInputVoltage() > 0L) {
                    return GTUtility.getTier(tTileEntity.getInputVoltage());
                }

                if (tTileEntity.getOutputVoltage() > 0L) {
                    return GTUtility.getTier(tTileEntity.getOutputVoltage());
                }

            }

        }

        return -1;
    }

    protected static boolean areItemStackEqual(ItemStack stackA, ItemStack stackB) {
        if (stackA == null && stackB == null) return true;
        if (stackA == null || stackB == null) return false;
        return stackA.isItemEqual(stackB) && ItemStack.areItemStackTagsEqual(stackA, stackB);
    }

}
