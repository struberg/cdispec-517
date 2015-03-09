/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.struct.cditest.genericproducer;

import javax.inject.Inject;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * DO NOT CHECK IN!
 * @author <a href="mailto:struberg@yahoo.de">Mark Struberg</a>
 */
@RunWith(Arquillian.class)
public class ParameterizedProducerTest
{

    @Deployment
    public static WebArchive deploy()
    {
        return ShrinkWrap.create(WebArchive.class, "parameterizedproducer.war")
                .addClass(RawProducerTest.class)
                .addClass(MyGenericBean.class)
                .addClass(MyGenericBeanProducer.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }


    private @Inject MyGenericBean<String> bean;


    @Test
    public void testGenericInjection()
    {
        Assert.assertEquals(42, bean.getMeaningOfLife());
    }

}
