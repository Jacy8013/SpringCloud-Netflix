package io.jacy.eureka.client.consumer;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Jacy
 */
@RestController
public class ConsumerController {
    private final DiscoveryClient client;

    private final EurekaClient eurekaClient;

    private final LoadBalancerClient lb;

    private final RestTemplate restTemplate;

    public ConsumerController(DiscoveryClient client, EurekaClient eurekaClient,
                              LoadBalancerClient lb, RestTemplate restTemplate) {
        this.client = client;
        this.eurekaClient = eurekaClient;
        this.lb = lb;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/euk/c1")
    public Object consumer1() {
        return "hello eureka client consumer";
    }

    @GetMapping("/euk/c2")
    public Object consumer2() {
        List<ServiceInstance> provider = client.getInstances("eureka-client-provider");
        for (ServiceInstance instance : provider) {
            System.out.println("=================: " + instance.getUri() + "/euk/provider");
        }
        return provider;
    }

    @GetMapping("/euk/c3")
    public Object consumer3() {
        List<InstanceInfo> instances = eurekaClient.getInstancesByVipAddress("eureka-client-provider", false);
        for (InstanceInfo instance : instances) {
            System.out.println("=================: " + "http://" + instance.getHostName() + ":" + instance.getPort() + "/euk/provider" + " , " + instance.getStatus());
        }
        return instances;
    }

    @GetMapping("/euk/c4")
    public Object consumer4() {
//        String url = lb.choose("eureka-client-provider").getUri() + "/euk/provider";
//        System.out.println(url);
        return restTemplate.getForEntity("http://EUREKA-CLIENT-PROVIDER/euk/provider", String.class);
    }
}
