package com.example.core.mernis;

//------------------------------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 9.0.0.0
//
// Created by Quasar Development 
//
// This class has been generated for test purposes only and cannot be used in any commercial project.
// To use it in commercial project, you need to generate this class again with Premium account.
// Check https://EasyWsdl.com/Payment/PremiumAccountDetails to see all benefits of Premium account.
//
// Licence: 1A868373920EE2FF06DD01C670AD41B4B0220C6A79BEB6A1D9142928D14554766C04D7056647FBD46F9ECE10A326A8348375D565EF71CC6CD6CF3BF436D46F34
//------------------------------------------------------------------------

import org.ksoap2.serialization.Marshal;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.util.UUID;


public class QVWMarshalGuid implements Marshal
{
    public java.lang.Object readInstance(XmlPullParser parser, java.lang.String namespace, java.lang.String name,PropertyInfo expected) throws IOException, XmlPullParserException
    {
        return UUID.fromString(parser.nextText());
    }

    public void register(SoapSerializationEnvelope cm)
    {
        cm.addMapping(cm.xsd, "guid", UUID.class, this);
    }

    public void writeInstance(XmlSerializer writer, java.lang.Object obj) throws IOException
    {
        writer.text(obj.toString());
    }
}