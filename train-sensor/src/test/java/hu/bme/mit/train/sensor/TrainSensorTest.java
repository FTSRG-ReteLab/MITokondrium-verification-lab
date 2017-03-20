package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.user.TrainUserImpl;

import static org.mockito.Mockito.*;

public class TrainSensorTest {
	private TrainSensorImpl ts;
	private TrainController mockController;
	private TrainUser mockUser;

	@Before
	public void before() {
		mockUser = mock(TrainUser.class);
		mockController = mock(TrainController.class);
		ts = new TrainSensorImpl(mockController, mockUser);
	}
	
	@Test
	public void speedLimitBelowNull_Alarm() {
		when(mockController.getReferenceSpeed()).thenReturn(0);
		ts.overrideSpeedLimit(-500);

		verify(mockUser, times(1)).setAlarmState(true);
	}
	@Test
	public void speedLimitAbove500_Alarm() {
		when(mockController.getReferenceSpeed()).thenReturn(0);
		ts.overrideSpeedLimit(1000);

		verify(mockUser, times(1)).setAlarmState(true);
	}
	@Test
	public void speedLimitIsOk_NoAlarm() {
		when(mockController.getReferenceSpeed()).thenReturn(0);
		ts.overrideSpeedLimit(200);

		verify(mockUser, times(0)).setAlarmState(false);
	}
	@Test
	public void relativeMargin_Alarm() {
		when(mockController.getReferenceSpeed()).thenReturn(150);
		ts.overrideSpeedLimit(50);

		verify(mockUser, times(1)).setAlarmState(true);
	}
}
