package com.ts.esdemo.search.client;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.ImmutableSettings.Builder;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ESDemoSearchClient {

	private static final Logger logger = LoggerFactory.getLogger(ESDemoSearchClient.class);

	private Client client;

	@Value("${elasticsearch.cluster.name}")
	private String esClusterName;

	@Value("#{'${elasticsearch.cluster.node.addresses}'.split(';')}")
	private String[] esClusterNodes;

	@PostConstruct
	public void initializeTransportClient() throws Exception {
		logger.debug("Initializing search client.");
		try {
			Builder settingsBuilder = ImmutableSettings.settingsBuilder();
			settingsBuilder.put("cluster.name", esClusterName);
			settingsBuilder.put("client", true);
			settingsBuilder.put("network.server", true);
			settingsBuilder.put("data", false);
			this.client = new TransportClient(settingsBuilder.build());
			for (String eachTransportNode : esClusterNodes) {
				String[] nodeDetails = eachTransportNode.split(":");
				client = ((TransportClient) this.client).addTransportAddress(
						new InetSocketTransportAddress(nodeDetails[0], Integer.valueOf(nodeDetails[1])));
			}
			// do some initial check to see if client is able to connect to the
			// cluster
			this.client.admin().cluster().health(new ClusterHealthRequest()).actionGet();
		} catch (Exception e) {
			logger.error("Error while initializing search client.", e);
			throw e;
		}
	}

	public Client getClient() {
		return client;
	}
}