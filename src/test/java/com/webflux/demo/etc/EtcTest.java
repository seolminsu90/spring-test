package com.webflux.demo.etc;

import com.webflux.demo.SpringWebfluxApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class EtcTest extends SpringWebfluxApplicationTests {
    @Test
    void monoTest(){
        log.info(" :::: monoTest :::: ");

        // Delete 후 Select를 테스트 하려고 했는데.. Void는 아예 flatMap 안타는 구나 싶어서 확인 하는 테스트
        Mono<Void> test = Mono.empty();
        test.flatMap(re -> {
            log.info("Void 라 변환할 데이터가 없어서 안나옴"); // 얘는 안나온다.
            return Mono.just("test");
        }).block();

        Mono<String> test1 = Mono.just("Not Empty");
        test1.flatMap(re -> {
            log.info("데이터 처리를 할 수 있어서 나옴"); // 얘는 나온다.
            return Mono.just("test");
        }).block();

        Mono<String> test2 = Mono.empty();
        test2.doOnSuccess(re -> {
            log.info("Void인 애의 후처리는 이걸 사용하기"); // 얘는 나온다. 근데 소모하고 끝날텐데 이후 처리는 콜백 지옥이 되는거 아닐까... zip 같은걸 활용 잘 해야할까 어렵다.
                                                        // ex) 조회->삭제->완료확인->다른처리->리턴 이런거면 .. . ....
        }).block();
    }

    @Test
    void fluxTest(){
        log.info(" :::: fluxTest :::: ");

        Flux<Integer> flx = Flux.range(1,5);
        Flux<Integer> flx2 = Flux.range(1,2);

        flx.zipWith(flx2).subscribe(f -> log.info("test {}", f));
    }
}
