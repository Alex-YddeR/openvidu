package io.openvidu.test.browsers;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

public class OperaUser extends BrowserUser {

	public OperaUser(String userName, int timeOfWaitInSeconds) {
		super(userName, timeOfWaitInSeconds);

		OperaOptions options = new OperaOptions();

		options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
		options.setAcceptInsecureCerts(true);
		options.addArguments("allow-file-access-from-files");
		options.addArguments("use-fake-device-for-media-stream");
		options.addArguments("use-fake-ui-for-media-stream");

		String REMOTE_URL = System.getProperty("REMOTE_URL_OPERA");
		if (REMOTE_URL != null) {
			log.info("Using URL {} to connect to remote web driver", REMOTE_URL);
			try {
				this.driver = new RemoteWebDriver(new URL(REMOTE_URL), options);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else {
			log.info("Using local web driver");
			this.driver = new OperaDriver(options);
		}

		this.driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(timeOfWaitInSeconds));
		this.configureDriver();
	}

}
