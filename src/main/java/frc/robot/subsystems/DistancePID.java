/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Add your docs here.
 */
public class DistancePID extends PIDSubsystem {

  double Output;
  /**
   * Add your docs here.
   */
  public DistancePID() {
    // Intert a subsystem name and PID values here
    super("DistancePID", 0.03, 0.0, 0);
    //arguments P, I, and D

    setAbsoluteTolerance(0.35);
    getPIDController().setContinuous(false);
    setInputRange(-10, 10);
    setOutputRange(-0.5, 0.5); //can make this half speed, etc 
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
  }

  public void resetPID(){
    getPIDController().reset();
  }

 
  public double getOutput() {
    return Output;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;

    return DriveBase.averageDistance;
    //eventually this should return a distance from calculations 
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);

    Output = (output);

  }
}
