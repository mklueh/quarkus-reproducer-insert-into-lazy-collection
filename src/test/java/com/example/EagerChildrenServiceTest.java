package com.example;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import org.junit.jupiter.api.Test;
import org.wildfly.common.Assert;

import javax.inject.Inject;

@QuarkusTest
public class EagerChildrenServiceTest {

    @Inject
    EagerChildrenService lazyChildrenService;

    @Test
    public void testSaveParentWithLazyChildren() {
        lazyChildrenService.saveParentWithEagerChildren()
                .invoke(Assert::assertNotNull)
                .onFailure().invoke(Throwable::printStackTrace)
                .subscribe().withSubscriber(UniAssertSubscriber.create())
                .awaitItem();
    }

    @Test
    void testSaveLazyChild() {
        lazyChildrenService.saveEagerChild()
                .invoke(Assert::assertNotNull)
                .onFailure().invoke(Throwable::printStackTrace)
                .subscribe().withSubscriber(UniAssertSubscriber.create())
                .awaitItem();
    }

    @Test
    public void testSaveParentWithLazyChildrenAndAnnotation() {
        lazyChildrenService.saveParentWithEagerChildrenAndAnnotation()
                .invoke(Assert::assertNotNull)
                .onFailure().invoke(Throwable::printStackTrace)
                .subscribe().withSubscriber(UniAssertSubscriber.create())
                .awaitItem();
    }


}