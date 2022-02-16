package com.example;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import org.junit.jupiter.api.Test;
import org.wildfly.common.Assert;

import javax.inject.Inject;

@QuarkusTest
public class LazyChildrenServiceTest {

    @Inject
    LazyChildrenService lazyChildrenService;

    @Test
    public void testSaveParentWithLazyChildren() {
        lazyChildrenService.saveParentWithLazyChildren()
                .invoke(Assert::assertNotNull)
                .onFailure().invoke(Throwable::printStackTrace)
                .subscribe().withSubscriber(UniAssertSubscriber.create())
                .awaitItem();
    }

    @Test
    void testSaveLazyChild() {
        lazyChildrenService.saveLazyChild()
                .invoke(Assert::assertNotNull)
                .onFailure().invoke(Throwable::printStackTrace)
                .subscribe().withSubscriber(UniAssertSubscriber.create())
                .awaitItem();
    }

    @Test
    public void testSaveParentWithLazyChildrenAndAnnotation() {
        lazyChildrenService.saveParentWithLazyChildrenAndAnnotation()
                .invoke(Assert::assertNotNull)
                .onFailure().invoke(Throwable::printStackTrace)
                .subscribe().withSubscriber(UniAssertSubscriber.create())
                .awaitItem();
    }


}