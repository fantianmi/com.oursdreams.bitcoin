/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/index.html
 */
package com.mvc.util;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.springframework.stereotype.Service;

/**
 * Abstract class that knows the protocol for validating a CAS ticket.
 * 
 * @author Scott Battaglia
 * @version $Revision$ $Date$
 * @since 3.1
 */
@Service
public class AbstractCasProtocolUrlBasedTicketValidator {

	HostnameVerifier hv = new HostnameVerifier() {
		public boolean verify(String urlHostName, SSLSession request) {
			System.out.println("Warning: URL Host: " + urlHostName + " vs. "
					+ request.getPeerHost());
			return true;
		}
	};

	public void trustAllHttpsCertificates() throws Exception {
		javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
		javax.net.ssl.TrustManager tm = new miTM();
		trustAllCerts[0] = tm;
		javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, null);
		javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
				.getSocketFactory());
	}

	static class miTM implements javax.net.ssl.TrustManager,
			javax.net.ssl.X509TrustManager {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(java.security.cert.X509Certificate[] certs,
				String authType) throws java.security.cert.CertificateException {
			return;
		}

		public void checkClientTrusted(java.security.cert.X509Certificate[] certs,
				String authType) throws java.security.cert.CertificateException {
			return;
		}
	}

}
