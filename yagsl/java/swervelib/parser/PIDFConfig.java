package swervelib.parser;

import edu.wpi.first.math.controller.PIDController;
import swervelib.parser.deserializer.PIDFRange;

/**
 * Hold the PIDF and Integral Zone values for a PID.
 */
public class PIDFConfig
{

  /**
   * Proportional Gain for PID.
   */
  public double p;
  /**
   * Integral Gain for PID.
   */
  public double i;
  /**
   * Derivative Gain for PID.
   */
  public double d;

}
