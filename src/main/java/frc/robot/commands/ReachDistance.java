/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.Robot;
import frc.robot.subsystems.DistancePID;
import frc.robot.subsystems.HeadingPID;
import com.kauailabs.navx.frc.AHRS;


public class ReachDistance extends Command {

  private final DistancePID distancePID = RobotMap.distancePID;
  private final HeadingPID headingPID = RobotMap.headingPID;
  private final AHRS ahrs = Robot.ahrs;

  private double distance;
  boolean check = false;

  public ReachDistance(double d) {

    setTimeout(3.5);

    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    distance = d;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    distancePID.enable();
    distancePID.resetPID();
    Robot.driveBase.enableDriveBase();
    ahrs.reset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    distancePID.setSetpoint(-distance); //should be distance =, can change to hard setpoint 
    headingPID.setSetpoint(0);
    Robot.driveBase.DriveAutonomous();
    check = true; 
    SmartDashboard.putBoolean("ExecuteMethodInReachDistance", check);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut()/*distancePID.onTarget()*/;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
