package org.joel.sample;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class SomeOtherRunner extends BlockJUnit4ClassRunner {

	public SomeOtherRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

}
