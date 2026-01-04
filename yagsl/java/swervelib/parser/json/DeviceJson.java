package swervelib.parser.json;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.TalonFXS;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.thethriftybot.devices.ThriftyNova;
import edu.wpi.first.math.system.plant.DCMotor;
import yams.mechanisms.config.SwerveDriveConfig;
import yams.mechanisms.swerve.SwerveDrive;
import yams.motorcontrollers.SmartMotorController;
import yams.motorcontrollers.SmartMotorControllerConfig;
import yams.motorcontrollers.local.NovaWrapper;
import yams.motorcontrollers.local.SparkWrapper;
import yams.motorcontrollers.remote.TalonFXSWrapper;
import yams.motorcontrollers.remote.TalonFXWrapper;

/**
 * Device JSON parsed class. Used to access the JSON data.
 */
public class DeviceJson
{

  /**
   * The device type
   */
  public String type;
  /**
   * The CAN ID or pin ID of the device.
   */
  public int    id;
  /**
   * SmartIO Channel.
   */
  public int    channel = 0;
  /**
   * The CAN bus name which the device resides on if using CAN.
   */
  public String canbus  = "";

  public DCMotor getDCMotor(String motorType)
  {
    switch (motorType)
    {
      case "neo2":
      case "neo":
        return DCMotor.getNEO(1);
      case "neo550":
        return DCMotor.getNeo550(1);
      case "vortex":
        return DCMotor.getNeoVortex(1);
      case "minion":
        return DCMotor.getMinion(1);
      case "krakenx44":
        return DCMotor.getKrakenX44(1);
      case "krakenx60":
        return DCMotor.getKrakenX60(1);
      case "pulsar":
        return new DCMotor(12, 3.1, 189, 1, 7500, 1);
      default:
        throw new IllegalArgumentException("Invalid motor type: " + motorType);
    }
  }

  public SmartMotorController getMotorController(SmartMotorControllerConfig config)
  {
    String[] subtypes            = type.split("_");
    String   motorControllerType = subtypes[0].toLowerCase();
    String   motorType           = subtypes[1].toLowerCase();
    DCMotor motor                = getDCMotor(motorType);
    switch (motorControllerType)
    {
      case "talonfx":
        return new TalonFXWrapper(new TalonFX(id, new CANBus(canbus)), motor, config);
      case "talonfxs":
        return new TalonFXSWrapper(new TalonFXS(id, new CANBus(canbus)), motor, config);
      case "sparkmax":
        return new SparkWrapper(new SparkMax(id, MotorType.kBrushless), motor, config);
      case "sparkflex":
        return new SparkWrapper(new SparkFlex(id, MotorType.kBrushless), motor, config);
      case "nova":
        switch (motorType)
        {
          case "neo":
          case "neo2":
          case "neo550":
          case "vortex":
            return new NovaWrapper(new ThriftyNova(id, ThriftyNova.MotorType.NEO), motor, config);
          case "minion":
            return new NovaWrapper(new ThriftyNova(id, ThriftyNova.MotorType.MINION), motor, config);
        }
      default:
        throw new IllegalArgumentException("Invalid motor controller type: " + motorControllerType);
    }
  }

  public SwerveDrive createDrive()
  {
    new SwerveDriveConfig()
  }

}
