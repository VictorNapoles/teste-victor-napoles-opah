package br.com.opah.testvictornapoles.broker.client;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.opah.testvictornapoles.broker.dto.Hotel;

@FeignClient(url="${broker.api.url}", name="broker")
public interface BrokerClient {
	
	@GetMapping("{id}")
	public Optional<List<Hotel>> findById(@PathVariable("id")Integer id);
	
	
	@GetMapping("avail/{idCity}")
	public Optional<List<Hotel>> findByCity(@PathVariable("idCity")Integer idCity);

}