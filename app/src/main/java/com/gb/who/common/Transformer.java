package com.gb.who.common;

import java.util.Collection;

public interface Transformer<I, O> {

    O transform(I input);

    Collection<O> transform(Collection<I> inputCollection);
}
