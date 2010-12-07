/*******************************************************************************
 * Copyright (c) 2009, 2010 Mountainminds GmbH & Co. KG and Contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Marc R. Hoffmann - initial API and implementation
 *    
 *******************************************************************************/
package org.jacoco.core.internal.flow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.Label;

/**
 * Unit tests for {@link LabelInfoTest}.
 * 
 * @author Marc R. Hoffmann
 * @version $qualified.bundle.version$
 */
public class LabelInfoTest {

	private Label label;

	@Before
	public void setup() {
		label = new Label();
	}

	@Test
	public void testDefaults() {
		assertFalse(LabelInfo.isMultiTarget(label));
		assertFalse(LabelInfo.isSuccessor(label));
		assertFalse(LabelInfo.isDone(label));
		assertEquals(LabelInfo.NO_PROBE, LabelInfo.getProbeId(label));
	}

	@Test
	public void testOtherInfoObject() {
		label.info = new Object();
		assertFalse(LabelInfo.isSuccessor(label));
	}

	@Test
	public void testSuccessor() {
		LabelInfo.setSuccessor(label);
		assertFalse(LabelInfo.isMultiTarget(label));
		assertTrue(LabelInfo.isSuccessor(label));
	}

	@Test
	public void testMultiTarget1() {
		LabelInfo.setTarget(label);
		assertFalse(LabelInfo.isMultiTarget(label));
		assertFalse(LabelInfo.isSuccessor(label));

		LabelInfo.setTarget(label);
		assertTrue(LabelInfo.isMultiTarget(label));
		assertFalse(LabelInfo.isSuccessor(label));
	}

	@Test
	public void testMultiTarget2() {
		LabelInfo.setSuccessor(label);
		assertFalse(LabelInfo.isMultiTarget(label));
		assertTrue(LabelInfo.isSuccessor(label));

		LabelInfo.setTarget(label);
		assertTrue(LabelInfo.isMultiTarget(label));
		assertTrue(LabelInfo.isSuccessor(label));
	}

	@Test
	public void testMultiTarget3() {
		LabelInfo.setTarget(label);
		assertFalse(LabelInfo.isMultiTarget(label));
		assertFalse(LabelInfo.isSuccessor(label));

		LabelInfo.setSuccessor(label);
		assertTrue(LabelInfo.isMultiTarget(label));
		assertTrue(LabelInfo.isSuccessor(label));
	}

	@Test
	public void testSetResetDone() {
		LabelInfo.setDone(label);
		assertTrue(LabelInfo.isDone(label));

		LabelInfo.resetDone(label);
		assertFalse(LabelInfo.isDone(label));
	}

	@Test
	public void testSetProbeId() {
		LabelInfo.setProbeId(label, 123);
		assertEquals(123, LabelInfo.getProbeId(label));
	}

}
