//gerekli library'leri import ediyoruz

package frc.robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


public class Robot extends TimedRobot {
  // robotu kontrol ettiğimz joystick'imizi tanımlıyoruz
  private Joystick joystick; 
  
  // her motor için motor sürücü tanımlıyoruz 
  private PWMVictorSPX leftMotor1 = new PWMVictorSPX(0);
  private PWMVictorSPX leftMotor2 = new PWMVictorSPX(1);
  private PWMVictorSPX rightMotor1 = new PWMVictorSPX(2);
  private PWMVictorSPX rightMotor2 = new PWMVictorSPX(3);

  //motor sürücüleri sağ ve sol olarak gruplandırıyoruz
  private MotorControllerGroup leftSpeedGroup = new MotorControllerGroup(leftMotor1, leftMotor2);
  private MotorControllerGroup rightSpeedGroup = new MotorControllerGroup(rightMotor1, rightMotor2);

  //robotun dönüşlerini sağlamak için differentialdrive objesi oluşturuyoruz
  DifferentialDrive drivetrain = new DifferentialDrive(leftSpeedGroup, rightSpeedGroup);

  //joystick'imizin butonlarını tanımlıyoruz
  JoystickButton joystick_button_1 = new JoystickButton(joystick,0);
  JoystickButton joystick_button_2 = new JoystickButton(joystick,1);

  // otonomda kullanmak için sayaç tanımlıyoruz
  private Timer sayac = new Timer();

  @Override
  public void robotInit() {
    //motorların aynı yönde çalışabilmesi için gruplardan birini ters çeviriyoruz(değerini negatif yapıyoruz)
    rightSpeedGroup.setInverted(true);
  }

  @Override
  public void robotPeriodic() {

  }

  @Override
  public void autonomousInit() {
    //sayacımızı başlatıyoruz
    sayac.start();

  }

  @Override
  public void autonomousPeriodic() {
    //robotun 3 saniye boyunca %50 hızla geriye doğru gitmesini sağlıyoruz
    if (sayac.get()<= 3)
      drivetrain.tankDrive(-0.5, -0.5);


  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() { 
    //joystick butonlarından gelen bilgilere göre motorları çalıştırıyoruz
    drivetrain.tankDrive(joystick.getRawAxis(1),joystick.getRawAxis(4));
      

  }


  @Override
  public void disabledInit() {}


  @Override
  public void disabledPeriodic() {}


  @Override
  public void testInit() {}


  @Override
  public void testPeriodic() {}
}
