package swervelib.parser.json.modules;


/**
 * Angle motor conversion factors composite JSON parse class.
 */
public class AngleGearingJson
{

  /**
   * Reduction ratio for the motor to the wheel. X where "X:1"
   */
  public double gearRatio;

  public boolean equals(DriveGearingJson o)
  {
    return o.gearRatio == gearRatio;
  }
}
