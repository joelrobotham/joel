package org.joel.sample;

import java.util.Collections;
import java.util.List;

public class CoordinatorImpl implements Coordinator {

	private final Coordinatee coordinatee;

	public CoordinatorImpl(Coordinatee coordinatee)
	{
		this.coordinatee = coordinatee;
	}
	
	/* (non-Javadoc)
	 * @see org.joel.sample.CoordinatorInterface#coordinate(boolean)
	 */
	public List<String> coordinate(boolean reallyCoordinate)
	{
		if (reallyCoordinate)
		{
			try
			{
				return Collections.singletonList(coordinatee.coordinate("please"));

			}
			catch (RuntimeException e)
			{
				coordinatee.uncoordinate();
			}
		}
		return null;
	}
	
	
}
