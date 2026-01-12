/**
 * ESB_TefBICE_ServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.3  Built on : Aug 10, 2007 (04:45:47 LKT)
 */
package cl.bice.wsc.tefws;


/*
 *  ESB_TefBICE_ServiceStub java implementation
 */
public class ESB_TefBICE_ServiceStub extends org.apache.axis2.client.Stub {
    protected org.apache.axis2.description.AxisOperation[] _operations;

    //hashmaps to keep the fault mapping
    private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
    private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
    private java.util.HashMap faultMessageMap = new java.util.HashMap();
    private javax.xml.namespace.QName[] opNameArray = null;

    /**
     *Constructor that takes in a configContext
     */
    public ESB_TefBICE_ServiceStub(
        org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(configurationContext, targetEndpoint, false);
    }

    /**
     * Constructor that takes in a configContext  and useseperate listner
     */
    public ESB_TefBICE_ServiceStub(
        org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint, boolean useSeparateListener)
        throws org.apache.axis2.AxisFault {
        //To populate AxisService
        populateAxisService();
        populateFaults();

        _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext,
                _service);

        configurationContext = _serviceClient.getServiceContext()
                                             .getConfigurationContext();

        _serviceClient.getOptions()
                      .setTo(new org.apache.axis2.addressing.EndpointReference(
                targetEndpoint));
        _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);
    }

    /**
     * Default Constructor
     */
    public ESB_TefBICE_ServiceStub(
        org.apache.axis2.context.ConfigurationContext configurationContext)
        throws org.apache.axis2.AxisFault {
        this(configurationContext,
            "http://ulmo:80/event/Internet/Financiero/TefBICE");
    }

    /**
     * Default Constructor
     */
    public ESB_TefBICE_ServiceStub() throws org.apache.axis2.AxisFault {
        this("http://ulmo:80/event/Internet/Financiero/TefBICE");
    }

    /**
     * Constructor taking the target endpoint
     */
    public ESB_TefBICE_ServiceStub(java.lang.String targetEndpoint)
        throws org.apache.axis2.AxisFault {
        this(null, targetEndpoint);
    }

    private void populateAxisService() throws org.apache.axis2.AxisFault {
        //creating the Service with a unique name
        _service = new org.apache.axis2.description.AxisService(
                "ESB_TefBICE_Service" + this.hashCode());

        //creating the operations
        org.apache.axis2.description.AxisOperation __operation;

        _operations = new org.apache.axis2.description.AxisOperation[1];

        __operation = new org.apache.axis2.description.OutInAxisOperation();

        __operation.setName(new javax.xml.namespace.QName(
                "http://xmlns.oracle.com/TefBICE", "process"));
        _service.addOperation(__operation);

        _operations[0] = __operation;
    }

    //populates the faults
    private void populateFaults() {
    }

    /**
     * Auto generated method signature
     * @see cl.bice.wsc.tefws.ESB_TefBICE_Service#process
     * @param tefBICEProcessRequest0
     */
    public cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub.TefBICEProcessResponse process(
        cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub.TefBICEProcessRequest tefBICEProcessRequest0)
        throws java.rmi.RemoteException {
        try {
            org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
            _operationClient.getOptions().setAction("process");
            _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

            addPropertyToOperationClient(_operationClient,
                org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
                "&");

            // create a message context
            org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(_operationClient.getOptions()
                                                        .getSoapVersionURI()),
                    tefBICEProcessRequest0,
                    optimizeContent(
                        new javax.xml.namespace.QName(
                            "http://xmlns.oracle.com/TefBICE", "process")));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            _messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            _operationClient.addMessageContext(_messageContext);

            //execute the operation client
            _operationClient.execute(true);

            org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

            java.lang.Object object = fromOM(_returnEnv.getBody()
                                                       .getFirstElement(),
                    cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub.TefBICEProcessResponse.class,
                    getEnvelopeNamespaces(_returnEnv));
            _messageContext.getTransportOut().getSender()
                           .cleanup(_messageContext);

            return (cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub.TefBICEProcessResponse) object;
        } catch (org.apache.axis2.AxisFault f) {
            org.apache.axiom.om.OMElement faultElt = f.getDetail();

            if (faultElt != null) {
                if (faultExceptionNameMap.containsKey(faultElt.getQName())) {
                    //make the fault by reflection
                    try {
                        java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(faultElt.getQName());
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.Exception ex = (java.lang.Exception) exceptionClass.newInstance();

                        //message class
                        java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,
                                messageClass, null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                new java.lang.Class[] { messageClass });
                        m.invoke(ex, new java.lang.Object[] { messageObject });

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    } catch (java.lang.ClassCastException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                } else {
                    throw f;
                }
            } else {
                throw f;
            }
        }
    }

    /**
     * Auto generated method signature for Asynchronous Invocations
     * @see cl.bice.wsc.tefws.ESB_TefBICE_Service#startprocess
     * @param tefBICEProcessRequest0
     */
    public void startprocess(
        cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub.TefBICEProcessRequest tefBICEProcessRequest0,
        final cl.bice.wsc.tefws.ESB_TefBICE_ServiceCallbackHandler callback)
        throws java.rmi.RemoteException {
        org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
        _operationClient.getOptions().setAction("process");
        _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

        addPropertyToOperationClient(_operationClient,
            org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
            "&");

        // create SOAP envelope with that payload
        org.apache.axiom.soap.SOAPEnvelope env = null;
        org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

        //Style is Doc.
        env = toEnvelope(getFactory(_operationClient.getOptions()
                                                    .getSoapVersionURI()),
                tefBICEProcessRequest0,
                optimizeContent(
                    new javax.xml.namespace.QName(
                        "http://xmlns.oracle.com/TefBICE", "process")));

        // adding SOAP soap_headers
        _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);

        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
                public void onMessage(
                    org.apache.axis2.context.MessageContext resultContext) {
                    try {
                        org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

                        java.lang.Object object = fromOM(resultEnv.getBody()
                                                                  .getFirstElement(),
                                cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub.TefBICEProcessResponse.class,
                                getEnvelopeNamespaces(resultEnv));
                        callback.receiveResultprocess((cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub.TefBICEProcessResponse) object);
                    } catch (org.apache.axis2.AxisFault e) {
                        callback.receiveErrorprocess(e);
                    }
                }

                public void onError(java.lang.Exception error) {
                    if (error instanceof org.apache.axis2.AxisFault) {
                        org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
                        org.apache.axiom.om.OMElement faultElt = f.getDetail();

                        if (faultElt != null) {
                            if (faultExceptionNameMap.containsKey(
                                        faultElt.getQName())) {
                                //make the fault by reflection
                                try {
                                    java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(faultElt.getQName());
                                    java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                                    java.lang.Exception ex = (java.lang.Exception) exceptionClass.newInstance();

                                    //message class
                                    java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(faultElt.getQName());
                                    java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                                    java.lang.Object messageObject = fromOM(faultElt,
                                            messageClass, null);
                                    java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                            new java.lang.Class[] { messageClass });
                                    m.invoke(ex,
                                        new java.lang.Object[] { messageObject });

                                    callback.receiveErrorprocess(new java.rmi.RemoteException(
                                            ex.getMessage(), ex));
                                } catch (java.lang.ClassCastException e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrorprocess(f);
                                } catch (java.lang.ClassNotFoundException e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrorprocess(f);
                                } catch (java.lang.NoSuchMethodException e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrorprocess(f);
                                } catch (java.lang.reflect.InvocationTargetException e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrorprocess(f);
                                } catch (java.lang.IllegalAccessException e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrorprocess(f);
                                } catch (java.lang.InstantiationException e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrorprocess(f);
                                } catch (org.apache.axis2.AxisFault e) {
                                    // we cannot intantiate the class - throw the original Axis fault
                                    callback.receiveErrorprocess(f);
                                }
                            } else {
                                callback.receiveErrorprocess(f);
                            }
                        } else {
                            callback.receiveErrorprocess(f);
                        }
                    } else {
                        callback.receiveErrorprocess(error);
                    }
                }

                public void onFault(
                    org.apache.axis2.context.MessageContext faultContext) {
                    org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
                    onError(fault);
                }

                public void onComplete() {
                    // Do nothing by default
                }
            });

        org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;

        if ((_operations[0].getMessageReceiver() == null) &&
                _operationClient.getOptions().isUseSeparateListener()) {
            _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
            _operations[0].setMessageReceiver(_callbackReceiver);
        }

        //execute the operation client
        _operationClient.execute(false);
    }

    /**
     *  A utility method that copies the namepaces from the SOAPEnvelope
     */
    private java.util.Map getEnvelopeNamespaces(
        org.apache.axiom.soap.SOAPEnvelope env) {
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();

        while (namespaceIterator.hasNext()) {
            org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
            returnMap.put(ns.getPrefix(), ns.getNamespaceURI());
        }

        return returnMap;
    }

    private boolean optimizeContent(javax.xml.namespace.QName opName) {
        if (opNameArray == null) {
            return false;
        }

        for (int i = 0; i < opNameArray.length; i++) {
            if (opName.equals(opNameArray[i])) {
                return true;
            }
        }

        return false;
    }

    private org.apache.axiom.om.OMElement toOM(
        cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub.TefBICEProcessRequest param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub.TefBICEProcessRequest.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub.TefBICEProcessResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub.TefBICEProcessResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub.TefBICEProcessRequest param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub.TefBICEProcessRequest.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    /* methods to provide back word compatibility */

    /**
     *  get the default envelope
     */
    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory) {
        return factory.getDefaultEnvelope();
    }

    private java.lang.Object fromOM(org.apache.axiom.om.OMElement param,
        java.lang.Class type, java.util.Map extraNamespaces)
        throws org.apache.axis2.AxisFault {
        try {
            if (cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub.TefBICEProcessRequest.class.equals(
                        type)) {
                return cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub.TefBICEProcessRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub.TefBICEProcessResponse.class.equals(
                        type)) {
                return cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub.TefBICEProcessResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

        return null;
    }

    //http://ulmo:80/event/Internet/Financiero/TefBICE
    public static class ExtensionMapper {
        public static java.lang.Object getTypeObject(
            java.lang.String namespaceURI, java.lang.String typeName,
            javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
            if ("http://xmlns.oracle.com/TefBICE".equals(namespaceURI) &&
                    "CONCEPTO_type0".equals(typeName)) {
                return CONCEPTO_type0.Factory.parse(reader);
            }

            if ("http://xmlns.oracle.com/TefBICE".equals(namespaceURI) &&
                    "DATOS_ABONO_type0".equals(typeName)) {
                return DATOS_ABONO_type0.Factory.parse(reader);
            }

            if ("http://xmlns.oracle.com/TefBICE".equals(namespaceURI) &&
                    "DATOS_CARGO_type0".equals(typeName)) {
                return DATOS_CARGO_type0.Factory.parse(reader);
            }

            if ("http://xmlns.oracle.com/TefBICE".equals(namespaceURI) &&
                    "CONTEXTO_type0".equals(typeName)) {
                return CONTEXTO_type0.Factory.parse(reader);
            }

            throw new org.apache.axis2.databinding.ADBException(
                "Unsupported type " + namespaceURI + " " + typeName);
        }
    }

    public static class CONCEPTO_type0 implements org.apache.axis2.databinding.ADBBean {
        /**
         * field for CANAL
         */
        protected java.lang.String localCANAL;

        /**
         * field for SUCURSAL
         */
        protected java.lang.String localSUCURSAL;

        /**
         * field for IDSERVICIO
         */
        protected java.lang.String localIDSERVICIO;

        /* This type was generated from the piece of schema that had
           name = CONCEPTO_type0
           Namespace URI = http://xmlns.oracle.com/TefBICE
           Namespace Prefix = ns1
         */
        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals("http://xmlns.oracle.com/TefBICE")) {
                return "ns1";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getCANAL() {
            return localCANAL;
        }

        /**
         * Auto generated setter method
         * @param param CANAL
         */
        public void setCANAL(java.lang.String param) {
            this.localCANAL = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getSUCURSAL() {
            return localSUCURSAL;
        }

        /**
         * Auto generated setter method
         * @param param SUCURSAL
         */
        public void setSUCURSAL(java.lang.String param) {
            this.localSUCURSAL = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getIDSERVICIO() {
            return localIDSERVICIO;
        }

        /**
         * Auto generated setter method
         * @param param IDSERVICIO
         */
        public void setIDSERVICIO(java.lang.String param) {
            this.localIDSERVICIO = param;
        }

        /**
         * isReaderMTOMAware
         * @return true if the reader supports MTOM
         */
        public static boolean isReaderMTOMAware(
            javax.xml.stream.XMLStreamReader reader) {
            boolean isReaderMTOMAware = false;

            try {
                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                            org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
            } catch (java.lang.IllegalArgumentException e) {
                isReaderMTOMAware = false;
            }

            return isReaderMTOMAware;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                    parentQName) {
                    public void serialize(
                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException {
                        CONCEPTO_type0.this.serialize(parentQName, factory,
                            xmlWriter);
                    }
                };

            return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName,
                factory, dataSource);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();

            if (namespace != null) {
                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                if (writerPrefix != null) {
                    xmlWriter.writeStartElement(namespace,
                        parentQName.getLocalPart());
                } else {
                    if (prefix == null) {
                        prefix = generatePrefix(namespace);
                    }

                    xmlWriter.writeStartElement(prefix,
                        parentQName.getLocalPart(), namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }
            } else {
                xmlWriter.writeStartElement(parentQName.getLocalPart());
            }

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "CANAL", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "CANAL");
                }
            } else {
                xmlWriter.writeStartElement("CANAL");
            }

            if (localCANAL == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "CANAL cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localCANAL);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "SUCURSAL", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "SUCURSAL");
                }
            } else {
                xmlWriter.writeStartElement("SUCURSAL");
            }

            if (localSUCURSAL == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "SUCURSAL cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localSUCURSAL);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "IDSERVICIO", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "IDSERVICIO");
                }
            } else {
                xmlWriter.writeStartElement("IDSERVICIO");
            }

            if (localIDSERVICIO == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "IDSERVICIO cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localIDSERVICIO);
            }

            xmlWriter.writeEndElement();

            xmlWriter.writeEndElement();
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(
            javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException {
            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "CANAL"));

            if (localCANAL != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localCANAL));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "CANAL cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "SUCURSAL"));

            if (localSUCURSAL != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localSUCURSAL));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "SUCURSAL cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "IDSERVICIO"));

            if (localIDSERVICIO != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localIDSERVICIO));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "IDSERVICIO cannot be null!!");
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                elementList.toArray(), attribList.toArray());
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static CONCEPTO_type0 parse(
                javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                CONCEPTO_type0 object = new CONCEPTO_type0();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"CONCEPTO_type0".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (CONCEPTO_type0) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "CANAL").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setCANAL(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "SUCURSAL").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setSUCURSAL(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "IDSERVICIO").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setIDSERVICIO(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class
    }

    public static class DATOS_CARGO_type0 implements org.apache.axis2.databinding.ADBBean {
        /**
         * field for RUTCLIENTE
         */
        protected java.lang.String localRUTCLIENTE;

        /**
         * field for MONEDACARGO
         */
        protected java.lang.String localMONEDACARGO;

        /**
         * field for BANCOCARGO
         */
        protected java.lang.String localBANCOCARGO;

        /**
         * field for CUENTACARGO
         */
        protected java.lang.String localCUENTACARGO;

        /**
         * field for TIPOCUENTACARGO
         */
        protected java.lang.String localTIPOCUENTACARGO;

        /**
         * field for MONTOCARGO
         */
        protected java.lang.String localMONTOCARGO;

        /**
         * field for DOCCARGO
         */
        protected java.lang.String localDOCCARGO;

        /* This type was generated from the piece of schema that had
           name = DATOS_CARGO_type0
           Namespace URI = http://xmlns.oracle.com/TefBICE
           Namespace Prefix = ns1
         */
        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals("http://xmlns.oracle.com/TefBICE")) {
                return "ns1";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getRUTCLIENTE() {
            return localRUTCLIENTE;
        }

        /**
         * Auto generated setter method
         * @param param RUTCLIENTE
         */
        public void setRUTCLIENTE(java.lang.String param) {
            this.localRUTCLIENTE = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getMONEDACARGO() {
            return localMONEDACARGO;
        }

        /**
         * Auto generated setter method
         * @param param MONEDACARGO
         */
        public void setMONEDACARGO(java.lang.String param) {
            this.localMONEDACARGO = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getBANCOCARGO() {
            return localBANCOCARGO;
        }

        /**
         * Auto generated setter method
         * @param param BANCOCARGO
         */
        public void setBANCOCARGO(java.lang.String param) {
            this.localBANCOCARGO = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getCUENTACARGO() {
            return localCUENTACARGO;
        }

        /**
         * Auto generated setter method
         * @param param CUENTACARGO
         */
        public void setCUENTACARGO(java.lang.String param) {
            this.localCUENTACARGO = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getTIPOCUENTACARGO() {
            return localTIPOCUENTACARGO;
        }

        /**
         * Auto generated setter method
         * @param param TIPOCUENTACARGO
         */
        public void setTIPOCUENTACARGO(java.lang.String param) {
            this.localTIPOCUENTACARGO = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getMONTOCARGO() {
            return localMONTOCARGO;
        }

        /**
         * Auto generated setter method
         * @param param MONTOCARGO
         */
        public void setMONTOCARGO(java.lang.String param) {
            this.localMONTOCARGO = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getDOCCARGO() {
            return localDOCCARGO;
        }

        /**
         * Auto generated setter method
         * @param param DOCCARGO
         */
        public void setDOCCARGO(java.lang.String param) {
            this.localDOCCARGO = param;
        }

        /**
         * isReaderMTOMAware
         * @return true if the reader supports MTOM
         */
        public static boolean isReaderMTOMAware(
            javax.xml.stream.XMLStreamReader reader) {
            boolean isReaderMTOMAware = false;

            try {
                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                            org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
            } catch (java.lang.IllegalArgumentException e) {
                isReaderMTOMAware = false;
            }

            return isReaderMTOMAware;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                    parentQName) {
                    public void serialize(
                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException {
                        DATOS_CARGO_type0.this.serialize(parentQName, factory,
                            xmlWriter);
                    }
                };

            return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName,
                factory, dataSource);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();

            if (namespace != null) {
                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                if (writerPrefix != null) {
                    xmlWriter.writeStartElement(namespace,
                        parentQName.getLocalPart());
                } else {
                    if (prefix == null) {
                        prefix = generatePrefix(namespace);
                    }

                    xmlWriter.writeStartElement(prefix,
                        parentQName.getLocalPart(), namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }
            } else {
                xmlWriter.writeStartElement(parentQName.getLocalPart());
            }

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "RUTCLIENTE", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "RUTCLIENTE");
                }
            } else {
                xmlWriter.writeStartElement("RUTCLIENTE");
            }

            if (localRUTCLIENTE == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "RUTCLIENTE cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localRUTCLIENTE);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "MONEDACARGO", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "MONEDACARGO");
                }
            } else {
                xmlWriter.writeStartElement("MONEDACARGO");
            }

            if (localMONEDACARGO == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "MONEDACARGO cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localMONEDACARGO);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "BANCOCARGO", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "BANCOCARGO");
                }
            } else {
                xmlWriter.writeStartElement("BANCOCARGO");
            }

            if (localBANCOCARGO == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "BANCOCARGO cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localBANCOCARGO);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "CUENTACARGO", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "CUENTACARGO");
                }
            } else {
                xmlWriter.writeStartElement("CUENTACARGO");
            }

            if (localCUENTACARGO == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "CUENTACARGO cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localCUENTACARGO);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "TIPOCUENTACARGO",
                        namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "TIPOCUENTACARGO");
                }
            } else {
                xmlWriter.writeStartElement("TIPOCUENTACARGO");
            }

            if (localTIPOCUENTACARGO == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "TIPOCUENTACARGO cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localTIPOCUENTACARGO);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "MONTOCARGO", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "MONTOCARGO");
                }
            } else {
                xmlWriter.writeStartElement("MONTOCARGO");
            }

            if (localMONTOCARGO == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "MONTOCARGO cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localMONTOCARGO);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "DOCCARGO", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "DOCCARGO");
                }
            } else {
                xmlWriter.writeStartElement("DOCCARGO");
            }

            if (localDOCCARGO == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "DOCCARGO cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localDOCCARGO);
            }

            xmlWriter.writeEndElement();

            xmlWriter.writeEndElement();
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(
            javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException {
            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "RUTCLIENTE"));

            if (localRUTCLIENTE != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localRUTCLIENTE));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "RUTCLIENTE cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "MONEDACARGO"));

            if (localMONEDACARGO != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localMONEDACARGO));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "MONEDACARGO cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "BANCOCARGO"));

            if (localBANCOCARGO != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localBANCOCARGO));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "BANCOCARGO cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "CUENTACARGO"));

            if (localCUENTACARGO != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localCUENTACARGO));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "CUENTACARGO cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "TIPOCUENTACARGO"));

            if (localTIPOCUENTACARGO != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localTIPOCUENTACARGO));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "TIPOCUENTACARGO cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "MONTOCARGO"));

            if (localMONTOCARGO != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localMONTOCARGO));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "MONTOCARGO cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "DOCCARGO"));

            if (localDOCCARGO != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localDOCCARGO));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "DOCCARGO cannot be null!!");
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                elementList.toArray(), attribList.toArray());
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static DATOS_CARGO_type0 parse(
                javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                DATOS_CARGO_type0 object = new DATOS_CARGO_type0();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"DATOS_CARGO_type0".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (DATOS_CARGO_type0) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "RUTCLIENTE").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setRUTCLIENTE(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "MONEDACARGO").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setMONEDACARGO(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "BANCOCARGO").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setBANCOCARGO(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "CUENTACARGO").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setCUENTACARGO(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE",
                                "TIPOCUENTACARGO").equals(reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setTIPOCUENTACARGO(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "MONTOCARGO").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setMONTOCARGO(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "DOCCARGO").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setDOCCARGO(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class
    }

    public static class DATOS_ABONO_type0 implements org.apache.axis2.databinding.ADBBean {
        /**
         * field for RUTABONO
         */
        protected java.lang.String localRUTABONO;

        /**
         * field for NOMBREABONO
         */
        protected java.lang.String localNOMBREABONO;

        /**
         * field for TIPOCUENTABONO
         */
        protected java.lang.String localTIPOCUENTABONO;

        /**
         * field for MONEDAABONO
         */
        protected java.lang.String localMONEDAABONO;

        /**
         * field for CUENTABONO
         */
        protected java.lang.String localCUENTABONO;

        /**
         * field for DOCABONO
         */
        protected java.lang.String localDOCABONO;

        /* This type was generated from the piece of schema that had
           name = DATOS_ABONO_type0
           Namespace URI = http://xmlns.oracle.com/TefBICE
           Namespace Prefix = ns1
         */
        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals("http://xmlns.oracle.com/TefBICE")) {
                return "ns1";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getRUTABONO() {
            return localRUTABONO;
        }

        /**
         * Auto generated setter method
         * @param param RUTABONO
         */
        public void setRUTABONO(java.lang.String param) {
            this.localRUTABONO = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getNOMBREABONO() {
            return localNOMBREABONO;
        }

        /**
         * Auto generated setter method
         * @param param NOMBREABONO
         */
        public void setNOMBREABONO(java.lang.String param) {
            this.localNOMBREABONO = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getTIPOCUENTABONO() {
            return localTIPOCUENTABONO;
        }

        /**
         * Auto generated setter method
         * @param param TIPOCUENTABONO
         */
        public void setTIPOCUENTABONO(java.lang.String param) {
            this.localTIPOCUENTABONO = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getMONEDAABONO() {
            return localMONEDAABONO;
        }

        /**
         * Auto generated setter method
         * @param param MONEDAABONO
         */
        public void setMONEDAABONO(java.lang.String param) {
            this.localMONEDAABONO = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getCUENTABONO() {
            return localCUENTABONO;
        }

        /**
         * Auto generated setter method
         * @param param CUENTABONO
         */
        public void setCUENTABONO(java.lang.String param) {
            this.localCUENTABONO = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getDOCABONO() {
            return localDOCABONO;
        }

        /**
         * Auto generated setter method
         * @param param DOCABONO
         */
        public void setDOCABONO(java.lang.String param) {
            this.localDOCABONO = param;
        }

        /**
         * isReaderMTOMAware
         * @return true if the reader supports MTOM
         */
        public static boolean isReaderMTOMAware(
            javax.xml.stream.XMLStreamReader reader) {
            boolean isReaderMTOMAware = false;

            try {
                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                            org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
            } catch (java.lang.IllegalArgumentException e) {
                isReaderMTOMAware = false;
            }

            return isReaderMTOMAware;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                    parentQName) {
                    public void serialize(
                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException {
                        DATOS_ABONO_type0.this.serialize(parentQName, factory,
                            xmlWriter);
                    }
                };

            return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName,
                factory, dataSource);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();

            if (namespace != null) {
                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                if (writerPrefix != null) {
                    xmlWriter.writeStartElement(namespace,
                        parentQName.getLocalPart());
                } else {
                    if (prefix == null) {
                        prefix = generatePrefix(namespace);
                    }

                    xmlWriter.writeStartElement(prefix,
                        parentQName.getLocalPart(), namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }
            } else {
                xmlWriter.writeStartElement(parentQName.getLocalPart());
            }

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "RUTABONO", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "RUTABONO");
                }
            } else {
                xmlWriter.writeStartElement("RUTABONO");
            }

            if (localRUTABONO == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "RUTABONO cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localRUTABONO);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "NOMBREABONO", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "NOMBREABONO");
                }
            } else {
                xmlWriter.writeStartElement("NOMBREABONO");
            }

            if (localNOMBREABONO == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "NOMBREABONO cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localNOMBREABONO);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "TIPOCUENTABONO",
                        namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "TIPOCUENTABONO");
                }
            } else {
                xmlWriter.writeStartElement("TIPOCUENTABONO");
            }

            if (localTIPOCUENTABONO == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "TIPOCUENTABONO cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localTIPOCUENTABONO);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "MONEDAABONO", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "MONEDAABONO");
                }
            } else {
                xmlWriter.writeStartElement("MONEDAABONO");
            }

            if (localMONEDAABONO == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "MONEDAABONO cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localMONEDAABONO);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "CUENTABONO", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "CUENTABONO");
                }
            } else {
                xmlWriter.writeStartElement("CUENTABONO");
            }

            if (localCUENTABONO == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "CUENTABONO cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localCUENTABONO);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "DOCABONO", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "DOCABONO");
                }
            } else {
                xmlWriter.writeStartElement("DOCABONO");
            }

            if (localDOCABONO == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "DOCABONO cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localDOCABONO);
            }

            xmlWriter.writeEndElement();

            xmlWriter.writeEndElement();
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(
            javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException {
            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "RUTABONO"));

            if (localRUTABONO != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localRUTABONO));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "RUTABONO cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "NOMBREABONO"));

            if (localNOMBREABONO != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localNOMBREABONO));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "NOMBREABONO cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "TIPOCUENTABONO"));

            if (localTIPOCUENTABONO != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localTIPOCUENTABONO));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "TIPOCUENTABONO cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "MONEDAABONO"));

            if (localMONEDAABONO != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localMONEDAABONO));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "MONEDAABONO cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "CUENTABONO"));

            if (localCUENTABONO != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localCUENTABONO));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "CUENTABONO cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "DOCABONO"));

            if (localDOCABONO != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localDOCABONO));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "DOCABONO cannot be null!!");
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                elementList.toArray(), attribList.toArray());
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static DATOS_ABONO_type0 parse(
                javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                DATOS_ABONO_type0 object = new DATOS_ABONO_type0();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"DATOS_ABONO_type0".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (DATOS_ABONO_type0) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "RUTABONO").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setRUTABONO(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "NOMBREABONO").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setNOMBREABONO(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE",
                                "TIPOCUENTABONO").equals(reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setTIPOCUENTABONO(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "MONEDAABONO").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setMONEDAABONO(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "CUENTABONO").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setCUENTABONO(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "DOCABONO").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setDOCABONO(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class
    }

    public static class TefBICEProcessRequest implements org.apache.axis2.databinding.ADBBean {
        public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://xmlns.oracle.com/TefBICE",
                "TefBICEProcessRequest", "ns1");

        /**
         * field for CONCEPTO
         */
        protected CONCEPTO_type0 localCONCEPTO;

        /**
         * field for CONTEXTO
         */
        protected CONTEXTO_type0 localCONTEXTO;

        /**
         * field for DATOS_CARGO
         * This was an Array!
         */
        protected DATOS_CARGO_type0[] localDATOS_CARGO;

        /**
         * field for DATOS_ABONO
         * This was an Array!
         */
        protected DATOS_ABONO_type0[] localDATOS_ABONO;

        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals("http://xmlns.oracle.com/TefBICE")) {
                return "ns1";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Auto generated getter method
         * @return CONCEPTO_type0
         */
        public CONCEPTO_type0 getCONCEPTO() {
            return localCONCEPTO;
        }

        /**
         * Auto generated setter method
         * @param param CONCEPTO
         */
        public void setCONCEPTO(CONCEPTO_type0 param) {
            this.localCONCEPTO = param;
        }

        /**
         * Auto generated getter method
         * @return CONTEXTO_type0
         */
        public CONTEXTO_type0 getCONTEXTO() {
            return localCONTEXTO;
        }

        /**
         * Auto generated setter method
         * @param param CONTEXTO
         */
        public void setCONTEXTO(CONTEXTO_type0 param) {
            this.localCONTEXTO = param;
        }

        /**
         * Auto generated getter method
         * @return DATOS_CARGO_type0[]
         */
        public DATOS_CARGO_type0[] getDATOS_CARGO() {
            return localDATOS_CARGO;
        }

        /**
         * validate the array for DATOS_CARGO
         */
        protected void validateDATOS_CARGO(DATOS_CARGO_type0[] param) {
            if ((param != null) && (param.length < 1)) {
                throw new java.lang.RuntimeException();
            }
        }

        /**
         * Auto generated setter method
         * @param param DATOS_CARGO
         */
        public void setDATOS_CARGO(DATOS_CARGO_type0[] param) {
            validateDATOS_CARGO(param);

            this.localDATOS_CARGO = param;
        }

        /**
         * Auto generated add method for the array for convenience
         * @param param DATOS_CARGO_type0
         */
        public void addDATOS_CARGO(DATOS_CARGO_type0 param) {
            if (localDATOS_CARGO == null) {
                localDATOS_CARGO = new DATOS_CARGO_type0[] {  };
            }

            java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localDATOS_CARGO);
            list.add(param);
            this.localDATOS_CARGO = (DATOS_CARGO_type0[]) list.toArray(new DATOS_CARGO_type0[list.size()]);
        }

        /**
         * Auto generated getter method
         * @return DATOS_ABONO_type0[]
         */
        public DATOS_ABONO_type0[] getDATOS_ABONO() {
            return localDATOS_ABONO;
        }

        /**
         * validate the array for DATOS_ABONO
         */
        protected void validateDATOS_ABONO(DATOS_ABONO_type0[] param) {
            if ((param != null) && (param.length < 1)) {
                throw new java.lang.RuntimeException();
            }
        }

        /**
         * Auto generated setter method
         * @param param DATOS_ABONO
         */
        public void setDATOS_ABONO(DATOS_ABONO_type0[] param) {
            validateDATOS_ABONO(param);

            this.localDATOS_ABONO = param;
        }

        /**
         * Auto generated add method for the array for convenience
         * @param param DATOS_ABONO_type0
         */
        public void addDATOS_ABONO(DATOS_ABONO_type0 param) {
            if (localDATOS_ABONO == null) {
                localDATOS_ABONO = new DATOS_ABONO_type0[] {  };
            }

            java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localDATOS_ABONO);
            list.add(param);
            this.localDATOS_ABONO = (DATOS_ABONO_type0[]) list.toArray(new DATOS_ABONO_type0[list.size()]);
        }

        /**
         * isReaderMTOMAware
         * @return true if the reader supports MTOM
         */
        public static boolean isReaderMTOMAware(
            javax.xml.stream.XMLStreamReader reader) {
            boolean isReaderMTOMAware = false;

            try {
                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                            org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
            } catch (java.lang.IllegalArgumentException e) {
                isReaderMTOMAware = false;
            }

            return isReaderMTOMAware;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                    MY_QNAME) {
                    public void serialize(
                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException {
                        TefBICEProcessRequest.this.serialize(MY_QNAME, factory,
                            xmlWriter);
                    }
                };

            return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME,
                factory, dataSource);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();

            if (namespace != null) {
                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                if (writerPrefix != null) {
                    xmlWriter.writeStartElement(namespace,
                        parentQName.getLocalPart());
                } else {
                    if (prefix == null) {
                        prefix = generatePrefix(namespace);
                    }

                    xmlWriter.writeStartElement(prefix,
                        parentQName.getLocalPart(), namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }
            } else {
                xmlWriter.writeStartElement(parentQName.getLocalPart());
            }

            if (localCONCEPTO == null) {
                throw new org.apache.axis2.databinding.ADBException(
                    "CONCEPTO cannot be null!!");
            }

            localCONCEPTO.serialize(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "CONCEPTO"), factory,
                xmlWriter);

            if (localCONTEXTO == null) {
                throw new org.apache.axis2.databinding.ADBException(
                    "CONTEXTO cannot be null!!");
            }

            localCONTEXTO.serialize(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "CONTEXTO"), factory,
                xmlWriter);

            if (localDATOS_CARGO != null) {
                for (int i = 0; i < localDATOS_CARGO.length; i++) {
                    if (localDATOS_CARGO[i] != null) {
                        localDATOS_CARGO[i].serialize(new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "DATOS_CARGO"),
                            factory, xmlWriter);
                    } else {
                        throw new org.apache.axis2.databinding.ADBException(
                            "DATOS_CARGO cannot be null!!");
                    }
                }
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "DATOS_CARGO cannot be null!!");
            }

            if (localDATOS_ABONO != null) {
                for (int i = 0; i < localDATOS_ABONO.length; i++) {
                    if (localDATOS_ABONO[i] != null) {
                        localDATOS_ABONO[i].serialize(new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "DATOS_ABONO"),
                            factory, xmlWriter);
                    } else {
                        throw new org.apache.axis2.databinding.ADBException(
                            "DATOS_ABONO cannot be null!!");
                    }
                }
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "DATOS_ABONO cannot be null!!");
            }

            xmlWriter.writeEndElement();
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(
            javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException {
            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "CONCEPTO"));

            if (localCONCEPTO == null) {
                throw new org.apache.axis2.databinding.ADBException(
                    "CONCEPTO cannot be null!!");
            }

            elementList.add(localCONCEPTO);

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "CONTEXTO"));

            if (localCONTEXTO == null) {
                throw new org.apache.axis2.databinding.ADBException(
                    "CONTEXTO cannot be null!!");
            }

            elementList.add(localCONTEXTO);

            if (localDATOS_CARGO != null) {
                for (int i = 0; i < localDATOS_CARGO.length; i++) {
                    if (localDATOS_CARGO[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "DATOS_CARGO"));
                        elementList.add(localDATOS_CARGO[i]);
                    } else {
                        throw new org.apache.axis2.databinding.ADBException(
                            "DATOS_CARGO cannot be null !!");
                    }
                }
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "DATOS_CARGO cannot be null!!");
            }

            if (localDATOS_ABONO != null) {
                for (int i = 0; i < localDATOS_ABONO.length; i++) {
                    if (localDATOS_ABONO[i] != null) {
                        elementList.add(new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "DATOS_ABONO"));
                        elementList.add(localDATOS_ABONO[i]);
                    } else {
                        throw new org.apache.axis2.databinding.ADBException(
                            "DATOS_ABONO cannot be null !!");
                    }
                }
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "DATOS_ABONO cannot be null!!");
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                elementList.toArray(), attribList.toArray());
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static TefBICEProcessRequest parse(
                javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                TefBICEProcessRequest object = new TefBICEProcessRequest();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"TefBICEProcessRequest".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (TefBICEProcessRequest) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    java.util.ArrayList list3 = new java.util.ArrayList();

                    java.util.ArrayList list4 = new java.util.ArrayList();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "CONCEPTO").equals(
                                reader.getName())) {
                        object.setCONCEPTO(CONCEPTO_type0.Factory.parse(reader));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "CONTEXTO").equals(
                                reader.getName())) {
                        object.setCONTEXTO(CONTEXTO_type0.Factory.parse(reader));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "DATOS_CARGO").equals(
                                reader.getName())) {
                        // Process the array and step past its final element's end.
                        list3.add(DATOS_CARGO_type0.Factory.parse(reader));

                        //loop until we find a start element that is not part of this array
                        boolean loopDone3 = false;

                        while (!loopDone3) {
                            // We should be at the end element, but make sure
                            while (!reader.isEndElement())
                                reader.next();

                            // Step out of this element
                            reader.next();

                            // Step to next element event.
                            while (!reader.isStartElement() &&
                                    !reader.isEndElement())
                                reader.next();

                            if (reader.isEndElement()) {
                                //two continuous end elements means we are exiting the xml structure
                                loopDone3 = true;
                            } else {
                                if (new javax.xml.namespace.QName(
                                            "http://xmlns.oracle.com/TefBICE",
                                            "DATOS_CARGO").equals(
                                            reader.getName())) {
                                    list3.add(DATOS_CARGO_type0.Factory.parse(
                                            reader));
                                } else {
                                    loopDone3 = true;
                                }
                            }
                        }

                        // call the converter utility  to convert and set the array
                        object.setDATOS_CARGO((DATOS_CARGO_type0[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                DATOS_CARGO_type0.class, list3));
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "DATOS_ABONO").equals(
                                reader.getName())) {
                        // Process the array and step past its final element's end.
                        list4.add(DATOS_ABONO_type0.Factory.parse(reader));

                        //loop until we find a start element that is not part of this array
                        boolean loopDone4 = false;

                        while (!loopDone4) {
                            // We should be at the end element, but make sure
                            while (!reader.isEndElement())
                                reader.next();

                            // Step out of this element
                            reader.next();

                            // Step to next element event.
                            while (!reader.isStartElement() &&
                                    !reader.isEndElement())
                                reader.next();

                            if (reader.isEndElement()) {
                                //two continuous end elements means we are exiting the xml structure
                                loopDone4 = true;
                            } else {
                                if (new javax.xml.namespace.QName(
                                            "http://xmlns.oracle.com/TefBICE",
                                            "DATOS_ABONO").equals(
                                            reader.getName())) {
                                    list4.add(DATOS_ABONO_type0.Factory.parse(
                                            reader));
                                } else {
                                    loopDone4 = true;
                                }
                            }
                        }

                        // call the converter utility  to convert and set the array
                        object.setDATOS_ABONO((DATOS_ABONO_type0[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                DATOS_ABONO_type0.class, list4));
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class
    }

    public static class CONTEXTO_type0 implements org.apache.axis2.databinding.ADBBean {
        /**
         * field for MODOTRX
         */
        protected java.lang.String localMODOTRX;

        /**
         * field for MODOINVOCACION
         */
        protected java.lang.String localMODOINVOCACION;

        /**
         * field for TRACENUMBER
         */
        protected java.lang.String localTRACENUMBER;

        /**
         * field for FECHACONTABLE
         */
        protected java.lang.String localFECHACONTABLE;

        /**
         * field for REFERENCIA
         */
        protected java.lang.String localREFERENCIA;

        /* This type was generated from the piece of schema that had
           name = CONTEXTO_type0
           Namespace URI = http://xmlns.oracle.com/TefBICE
           Namespace Prefix = ns1
         */
        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals("http://xmlns.oracle.com/TefBICE")) {
                return "ns1";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getMODOTRX() {
            return localMODOTRX;
        }

        /**
         * Auto generated setter method
         * @param param MODOTRX
         */
        public void setMODOTRX(java.lang.String param) {
            this.localMODOTRX = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getMODOINVOCACION() {
            return localMODOINVOCACION;
        }

        /**
         * Auto generated setter method
         * @param param MODOINVOCACION
         */
        public void setMODOINVOCACION(java.lang.String param) {
            this.localMODOINVOCACION = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getTRACENUMBER() {
            return localTRACENUMBER;
        }

        /**
         * Auto generated setter method
         * @param param TRACENUMBER
         */
        public void setTRACENUMBER(java.lang.String param) {
            this.localTRACENUMBER = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getFECHACONTABLE() {
            return localFECHACONTABLE;
        }

        /**
         * Auto generated setter method
         * @param param FECHACONTABLE
         */
        public void setFECHACONTABLE(java.lang.String param) {
            this.localFECHACONTABLE = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getREFERENCIA() {
            return localREFERENCIA;
        }

        /**
         * Auto generated setter method
         * @param param REFERENCIA
         */
        public void setREFERENCIA(java.lang.String param) {
            this.localREFERENCIA = param;
        }

        /**
         * isReaderMTOMAware
         * @return true if the reader supports MTOM
         */
        public static boolean isReaderMTOMAware(
            javax.xml.stream.XMLStreamReader reader) {
            boolean isReaderMTOMAware = false;

            try {
                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                            org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
            } catch (java.lang.IllegalArgumentException e) {
                isReaderMTOMAware = false;
            }

            return isReaderMTOMAware;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                    parentQName) {
                    public void serialize(
                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException {
                        CONTEXTO_type0.this.serialize(parentQName, factory,
                            xmlWriter);
                    }
                };

            return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(parentQName,
                factory, dataSource);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();

            if (namespace != null) {
                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                if (writerPrefix != null) {
                    xmlWriter.writeStartElement(namespace,
                        parentQName.getLocalPart());
                } else {
                    if (prefix == null) {
                        prefix = generatePrefix(namespace);
                    }

                    xmlWriter.writeStartElement(prefix,
                        parentQName.getLocalPart(), namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }
            } else {
                xmlWriter.writeStartElement(parentQName.getLocalPart());
            }

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "MODOTRX", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "MODOTRX");
                }
            } else {
                xmlWriter.writeStartElement("MODOTRX");
            }

            if (localMODOTRX == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "MODOTRX cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localMODOTRX);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "MODOINVOCACION",
                        namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "MODOINVOCACION");
                }
            } else {
                xmlWriter.writeStartElement("MODOINVOCACION");
            }

            if (localMODOINVOCACION == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "MODOINVOCACION cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localMODOINVOCACION);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "TRACENUMBER", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "TRACENUMBER");
                }
            } else {
                xmlWriter.writeStartElement("TRACENUMBER");
            }

            if (localTRACENUMBER == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "TRACENUMBER cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localTRACENUMBER);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "FECHACONTABLE",
                        namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "FECHACONTABLE");
                }
            } else {
                xmlWriter.writeStartElement("FECHACONTABLE");
            }

            if (localFECHACONTABLE == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "FECHACONTABLE cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localFECHACONTABLE);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "REFERENCIA", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "REFERENCIA");
                }
            } else {
                xmlWriter.writeStartElement("REFERENCIA");
            }

            if (localREFERENCIA == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "REFERENCIA cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localREFERENCIA);
            }

            xmlWriter.writeEndElement();

            xmlWriter.writeEndElement();
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(
            javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException {
            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "MODOTRX"));

            if (localMODOTRX != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localMODOTRX));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "MODOTRX cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "MODOINVOCACION"));

            if (localMODOINVOCACION != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localMODOINVOCACION));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "MODOINVOCACION cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "TRACENUMBER"));

            if (localTRACENUMBER != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localTRACENUMBER));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "TRACENUMBER cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "FECHACONTABLE"));

            if (localFECHACONTABLE != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localFECHACONTABLE));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "FECHACONTABLE cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "REFERENCIA"));

            if (localREFERENCIA != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localREFERENCIA));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "REFERENCIA cannot be null!!");
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                elementList.toArray(), attribList.toArray());
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static CONTEXTO_type0 parse(
                javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                CONTEXTO_type0 object = new CONTEXTO_type0();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"CONTEXTO_type0".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (CONTEXTO_type0) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "MODOTRX").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setMODOTRX(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE",
                                "MODOINVOCACION").equals(reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setMODOINVOCACION(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "TRACENUMBER").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setTRACENUMBER(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE",
                                "FECHACONTABLE").equals(reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setFECHACONTABLE(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "REFERENCIA").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setREFERENCIA(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class
    }

    public static class TefBICEProcessResponse implements org.apache.axis2.databinding.ADBBean {
        public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://xmlns.oracle.com/TefBICE",
                "TefBICEProcessResponse", "ns1");

        /**
         * field for ESTADO
         */
        protected java.lang.String localESTADO;

        /**
         * field for DESCRIPCION
         */
        protected java.lang.String localDESCRIPCION;

        /**
         * field for IDOPERACIONFCC
         */
        protected java.lang.String localIDOPERACIONFCC;

        /**
         * field for NROREFERENCIA
         */
        protected java.lang.String localNROREFERENCIA;

        /**
         * field for FECHACONTABLE_OUT
         */
        protected java.lang.String localFECHACONTABLE_OUT;

        /**
         * field for FECHA
         */
        protected java.lang.String localFECHA;

        /**
         * field for FECHA_IN
         */
        protected java.lang.String localFECHA_IN;

        /**
         * field for FECHA_OUT
         */
        protected java.lang.String localFECHA_OUT;

        /**
         * field for DURACION
         */
        protected java.lang.String localDURACION;

        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals("http://xmlns.oracle.com/TefBICE")) {
                return "ns1";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getESTADO() {
            return localESTADO;
        }

        /**
         * Auto generated setter method
         * @param param ESTADO
         */
        public void setESTADO(java.lang.String param) {
            this.localESTADO = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getDESCRIPCION() {
            return localDESCRIPCION;
        }

        /**
         * Auto generated setter method
         * @param param DESCRIPCION
         */
        public void setDESCRIPCION(java.lang.String param) {
            this.localDESCRIPCION = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getIDOPERACIONFCC() {
            return localIDOPERACIONFCC;
        }

        /**
         * Auto generated setter method
         * @param param IDOPERACIONFCC
         */
        public void setIDOPERACIONFCC(java.lang.String param) {
            this.localIDOPERACIONFCC = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getNROREFERENCIA() {
            return localNROREFERENCIA;
        }

        /**
         * Auto generated setter method
         * @param param NROREFERENCIA
         */
        public void setNROREFERENCIA(java.lang.String param) {
            this.localNROREFERENCIA = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getFECHACONTABLE_OUT() {
            return localFECHACONTABLE_OUT;
        }

        /**
         * Auto generated setter method
         * @param param FECHACONTABLE_OUT
         */
        public void setFECHACONTABLE_OUT(java.lang.String param) {
            this.localFECHACONTABLE_OUT = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getFECHA() {
            return localFECHA;
        }

        /**
         * Auto generated setter method
         * @param param FECHA
         */
        public void setFECHA(java.lang.String param) {
            this.localFECHA = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getFECHA_IN() {
            return localFECHA_IN;
        }

        /**
         * Auto generated setter method
         * @param param FECHA_IN
         */
        public void setFECHA_IN(java.lang.String param) {
            this.localFECHA_IN = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getFECHA_OUT() {
            return localFECHA_OUT;
        }

        /**
         * Auto generated setter method
         * @param param FECHA_OUT
         */
        public void setFECHA_OUT(java.lang.String param) {
            this.localFECHA_OUT = param;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getDURACION() {
            return localDURACION;
        }

        /**
         * Auto generated setter method
         * @param param DURACION
         */
        public void setDURACION(java.lang.String param) {
            this.localDURACION = param;
        }

        /**
         * isReaderMTOMAware
         * @return true if the reader supports MTOM
         */
        public static boolean isReaderMTOMAware(
            javax.xml.stream.XMLStreamReader reader) {
            boolean isReaderMTOMAware = false;

            try {
                isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(
                            org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
            } catch (java.lang.IllegalArgumentException e) {
                isReaderMTOMAware = false;
            }

            return isReaderMTOMAware;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            org.apache.axiom.om.OMDataSource dataSource = new org.apache.axis2.databinding.ADBDataSource(this,
                    MY_QNAME) {
                    public void serialize(
                        org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                        throws javax.xml.stream.XMLStreamException {
                        TefBICEProcessResponse.this.serialize(MY_QNAME,
                            factory, xmlWriter);
                    }
                };

            return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(MY_QNAME,
                factory, dataSource);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory,
            org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();

            if (namespace != null) {
                java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

                if (writerPrefix != null) {
                    xmlWriter.writeStartElement(namespace,
                        parentQName.getLocalPart());
                } else {
                    if (prefix == null) {
                        prefix = generatePrefix(namespace);
                    }

                    xmlWriter.writeStartElement(prefix,
                        parentQName.getLocalPart(), namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }
            } else {
                xmlWriter.writeStartElement(parentQName.getLocalPart());
            }

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "ESTADO", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "ESTADO");
                }
            } else {
                xmlWriter.writeStartElement("ESTADO");
            }

            if (localESTADO == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "ESTADO cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localESTADO);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "DESCRIPCION", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "DESCRIPCION");
                }
            } else {
                xmlWriter.writeStartElement("DESCRIPCION");
            }

            if (localDESCRIPCION == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "DESCRIPCION cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localDESCRIPCION);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "IDOPERACIONFCC",
                        namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "IDOPERACIONFCC");
                }
            } else {
                xmlWriter.writeStartElement("IDOPERACIONFCC");
            }

            if (localIDOPERACIONFCC == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "IDOPERACIONFCC cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localIDOPERACIONFCC);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "NROREFERENCIA",
                        namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "NROREFERENCIA");
                }
            } else {
                xmlWriter.writeStartElement("NROREFERENCIA");
            }

            if (localNROREFERENCIA == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "NROREFERENCIA cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localNROREFERENCIA);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "FECHACONTABLE_OUT",
                        namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "FECHACONTABLE_OUT");
                }
            } else {
                xmlWriter.writeStartElement("FECHACONTABLE_OUT");
            }

            if (localFECHACONTABLE_OUT == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "FECHACONTABLE_OUT cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localFECHACONTABLE_OUT);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "FECHA", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "FECHA");
                }
            } else {
                xmlWriter.writeStartElement("FECHA");
            }

            if (localFECHA == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "FECHA cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localFECHA);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "FECHA_IN", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "FECHA_IN");
                }
            } else {
                xmlWriter.writeStartElement("FECHA_IN");
            }

            if (localFECHA_IN == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "FECHA_IN cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localFECHA_IN);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "FECHA_OUT", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "FECHA_OUT");
                }
            } else {
                xmlWriter.writeStartElement("FECHA_OUT");
            }

            if (localFECHA_OUT == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "FECHA_OUT cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localFECHA_OUT);
            }

            xmlWriter.writeEndElement();

            namespace = "http://xmlns.oracle.com/TefBICE";

            if (!namespace.equals("")) {
                prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    xmlWriter.writeStartElement(prefix, "DURACION", namespace);
                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                } else {
                    xmlWriter.writeStartElement(namespace, "DURACION");
                }
            } else {
                xmlWriter.writeStartElement("DURACION");
            }

            if (localDURACION == null) {
                // write the nil attribute
                throw new org.apache.axis2.databinding.ADBException(
                    "DURACION cannot be null!!");
            } else {
                xmlWriter.writeCharacters(localDURACION);
            }

            xmlWriter.writeEndElement();

            xmlWriter.writeEndElement();
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            xmlWriter.writeAttribute(namespace, attName, attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace, attName, attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         * databinding method to get an XML representation of this object
         *
         */
        public javax.xml.stream.XMLStreamReader getPullParser(
            javax.xml.namespace.QName qName)
            throws org.apache.axis2.databinding.ADBException {
            java.util.ArrayList elementList = new java.util.ArrayList();
            java.util.ArrayList attribList = new java.util.ArrayList();

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "ESTADO"));

            if (localESTADO != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localESTADO));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "ESTADO cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "DESCRIPCION"));

            if (localDESCRIPCION != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localDESCRIPCION));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "DESCRIPCION cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "IDOPERACIONFCC"));

            if (localIDOPERACIONFCC != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localIDOPERACIONFCC));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "IDOPERACIONFCC cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "NROREFERENCIA"));

            if (localNROREFERENCIA != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localNROREFERENCIA));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "NROREFERENCIA cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "FECHACONTABLE_OUT"));

            if (localFECHACONTABLE_OUT != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localFECHACONTABLE_OUT));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "FECHACONTABLE_OUT cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "FECHA"));

            if (localFECHA != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localFECHA));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "FECHA cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "FECHA_IN"));

            if (localFECHA_IN != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localFECHA_IN));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "FECHA_IN cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "FECHA_OUT"));

            if (localFECHA_OUT != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localFECHA_OUT));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "FECHA_OUT cannot be null!!");
            }

            elementList.add(new javax.xml.namespace.QName(
                    "http://xmlns.oracle.com/TefBICE", "DURACION"));

            if (localDURACION != null) {
                elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        localDURACION));
            } else {
                throw new org.apache.axis2.databinding.ADBException(
                    "DURACION cannot be null!!");
            }

            return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName,
                elementList.toArray(), attribList.toArray());
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static TefBICEProcessResponse parse(
                javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                TefBICEProcessResponse object = new TefBICEProcessResponse();

                int event;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"TefBICEProcessResponse".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (TefBICEProcessResponse) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "ESTADO").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setESTADO(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "DESCRIPCION").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setDESCRIPCION(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE",
                                "IDOPERACIONFCC").equals(reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setIDOPERACIONFCC(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE",
                                "NROREFERENCIA").equals(reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setNROREFERENCIA(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE",
                                "FECHACONTABLE_OUT").equals(reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setFECHACONTABLE_OUT(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "FECHA").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setFECHA(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "FECHA_IN").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setFECHA_IN(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "FECHA_OUT").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setFECHA_OUT(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://xmlns.oracle.com/TefBICE", "DURACION").equals(
                                reader.getName())) {
                        java.lang.String content = reader.getElementText();

                        object.setDURACION(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        // A start element we are not expecting indicates an invalid parameter was passed
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getLocalName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class
    }
}