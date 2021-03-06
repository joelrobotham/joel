package org.joel.sample;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CoordinatorTest {

	@Mock
	private CoordinateeImpl coordinatee;
	private Coordinator coordinator;
	
	@Before
	public void createSubject()
	{
		coordinator = new CoordinatorImpl(coordinatee);
	}
	
	@Test
	public void coordinatorShouldAskNicelyWhenCoordinating()
	{
		coordinator.coordinate(true);
		
		verify(coordinatee).coordinate("please");
	}
	
	@Test
	public void coordinatorShouldAskNicelyWhenCoordinatingUsingCapture()
	{
		coordinator.coordinate(true);
		
		ArgumentCaptor<String> coordinateRequestCapture = new ArgumentCaptor<String>();
		verify(coordinatee).coordinate(coordinateRequestCapture.capture());
		
		assertThat(coordinateRequestCapture.getValue(), equalTo("please"));
	}
	
	@Test
	public void coordinatorShouldReturnTheResponseFromTheCoordinateeWhenSuccesful()
	{
		List<String> expectedValues = new ArrayList<String>();
		expectedValues.add("sure, no probs");
		
		when(coordinatee.coordinate("please")).thenReturn("sure, no probs");
		
		List<String> result = coordinator.coordinate(true);
		assertEquals("sure, no probs", result.get(0));
		
		//assertThat(result, hasItem(equalTo("sure, no probs")));
		//assertThat(result, hasItems((expectedValues.toArray(new String[0]))));
	}
	

	@Test
	public void coordinatorShouldNotCallCoordinateeIfAskedNotTo()
	{
		coordinator.coordinate(false);
		
		verify(coordinatee, never()).coordinate((String)any());
	}
	
	@Test
	public void coordinateeThrowingRuntimeExceptionShouldGetUncoordinated()
	{
		doThrow(new RuntimeException("blah")).when(coordinatee).coordinate(anyString());
		
		coordinator.coordinate(true);
		
		verify(coordinatee).uncoordinate();
	}
}
