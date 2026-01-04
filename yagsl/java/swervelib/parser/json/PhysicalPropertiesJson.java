package swervelib.parser.json;

import static edu.wpi.first.units.Units.Kilogram;
import static edu.wpi.first.units.Units.Pounds;

import swervelib.parser.json.modules.ConversionFactorsJson;

/**
 * {@link swervelib.parser.SwerveModulePhysicalCharacteristics} parsed data. Used to configure the SwerveModule.
 */
public class PhysicalPropertiesJson
{
  /**
   * Conversion Factors composition. Auto-calculates the conversion factors.
   */
  public ConversionFactorsJson conversionFactors              = new ConversionFactorsJson();
  /**
   * The current limit in AMPs to apply to the motors.
   */
  public MotorConfigInt        currentLimit                   = new MotorConfigInt(40, 20);

  /**
   * Create the physical characteristics based off the parsed data.
   *
   * @return {@link SwerveModulePhysicalCharacteristics} based on parsed data.
   */
  public SwerveModulePhysicalCharacteristics createPhysicalProperties()
  {
    // Setup deprecation notice.
    if (conversionFactor.drive != 0 && conversionFactor.angle != 0 && conversionFactors.isDriveEmpty() &&
        conversionFactors.isAngleEmpty())
    {
      throw new RuntimeException(
                "\n'conversionFactor': {'drive': " + conversionFactor.drive + ", 'angle': " + conversionFactor.angle +
                "} \nis deprecated, please use\n" +
                "'conversionFactors': {'drive': {'factor': " + conversionFactor.drive + "}, 'angle': {'factor': " +
                conversionFactor.angle + "} }");
    }

    return new SwerveModulePhysicalCharacteristics(
        conversionFactors,
        wheelGripCoefficientOfFriction,
        optimalVoltage,
        currentLimit.drive,
        currentLimit.angle,
        rampRate.drive,
        rampRate.angle,
        friction.drive,
        friction.angle,
        steerRotationalInertia,
        Pounds.of(robotMass).in(Kilogram));
  }
}

