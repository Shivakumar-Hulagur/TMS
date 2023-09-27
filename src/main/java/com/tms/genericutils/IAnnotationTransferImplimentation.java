package com.tms.genericutils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class IAnnotationTransferImplimentation implements org.testng.internal.annotations.IAnnotationTransformer{


	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod,
			Class<?> occurringClazz) {
		annotation.setRetryAnalyzer(com.tms.genericutils.RetryAnalyzerClass.class);
	}

}
