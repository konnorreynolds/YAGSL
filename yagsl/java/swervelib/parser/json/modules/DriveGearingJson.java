package swervelib.parser.json.modules;

/**
 * Drive motor composite JSON parse class.
 */
public class DriveGearingJson
{

  /**
   * Reduction ratio for the motor to the wheel. X where "X:1"
   */
  public double gearRatio;
  /**
   * Diameter of the wheel in inches.
   */
  public double diameter;

  public boolean equals(DriveGearingJson o)
  {
    return o.gearRatio == gearRatio && o.diameter == diameter;
  }
}
