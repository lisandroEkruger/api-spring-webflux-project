package com.api.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.time.Duration;
import java.util.stream.Stream;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testMonoBasic(){

		Mono<String> error = Mono.error(new RuntimeException("Mono which produces error when subscribe to him"));

		Mono<String> mono = Mono
				.just("Mono is a publisher with 0 or 1 item")
				.log()
				.then(error);

		//consume the mono by subscribing
		mono.subscribe(
				data -> System.out.println(data), //onNext
				throwable -> System.out.println(throwable), //onError
				() -> System.out.println("Completed") //onCompleted
		);

		//error.subscribe(System.out::println);
	}
	@Test
	void testMonoTuples(){
		Mono<String> m1 = Mono.just("Hello");
		Mono<String> m2 = Mono.just("world");
		Mono<String> m3 = Mono.just("in Spain");

		Mono<Tuple2<String, String>> tuple2Mono = Mono.zip(m1, m2);

		//zip.subscribe(x -> x.forEach(System.out::println));
		//zip.subscribe(x -> System.out.println(x));
		//tuple2Mono.subscribe(x -> x.getT1());

		Mono<Tuple3<String, String, String>> tuple3Mono = Mono.zip(m1, m2, m3);
		tuple3Mono.subscribe(System.out::println);
	}

	@Test
	void testMonoMapAndFlux() throws InterruptedException {
		Mono<String> m1 = Mono.just("Hello");
		Mono<String> m2 = Mono.just("world");

		Mono<String> map = m1.map(String::toUpperCase);
		//map.subscribe(System.out::println);

		Mono<String[]> flatMap = m1.flatMap(x -> Mono.just(x.split("")));
//		flatMap.subscribe(x -> {
//			for (String s: x){
//				System.out.println(s);
//			}
//		});
		//Publisher that emits 0 to N elements, and then completes (successfully or with an error).
		Flux<String> stringFlux = m1.flatMapMany(x -> Flux.just(x.split("")));
		//stringFlux.subscribe(System.out::println);

		Flux<String> concatWith = m1.concatWith(m2)
				.log()
				.delayElements(Duration.ofSeconds(2));

		Thread.sleep(2000);
		concatWith.subscribe(x -> {
			System.out.println(Thread.currentThread().getName());
			System.out.println(x);
		});

	}

	@Test
	void testHandleErrors() throws InterruptedException {

		Flux.just(1,2,3,4,5,6,7)
				.concatWith(Flux.error(new RuntimeException()))
				.onErrorReturn(-1)
				.subscribe();

		Flux.just(1,2,3,4,5)
				.map(x -> {
					if (x == 1) throw new RuntimeException();

					return x;
				})
				.onErrorContinue((throwable, o) -> System.out.println("Excepcion: "+throwable+", provocada por: " + o))
				.log()
				.subscribe();

	}

	@Test
	void testColdAndHotPublisher() throws InterruptedException {

		Flux<String> stringFlux = Flux.fromStream(this::getVideos)
				.delayElements(Duration.ofSeconds(1))
				.share(); //From cold to hot publisher

		stringFlux.subscribe(part -> System.out.println("Subscriber 1: " + part));

		//Thread.sleep(5000);

		stringFlux.subscribe(part -> System.out.println("Subscriber 2: " + part));

		//Thread.sleep(10000);
	}

	public Stream<String> getVideos(){
		System.out.println("Request for video");
		return Stream.of("part 1","part 2","part 3","part 4","part 5");
	}

}
