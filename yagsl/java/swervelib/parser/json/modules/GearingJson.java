package swervelib.parser.json.modules;

/**
 * Conversion Factors parsed JSON class
 */
public class GearingJson
{

  /**
   * Drive motor conversion factors composition.
   */
  public DriveGearingJson drive = new DriveGearingJson();
  /**
   * Angle motor conversion factors composition.
   */
  public AngleGearingJson angle = new AngleGearingJson();

}
