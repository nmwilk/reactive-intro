# Reactive Introduction

Various example of Rx compared to conventional pull/callback based code.

Tests don't test anything, they're just used to execute code.


## Notes from talk

Rx is short for Reactive Extensions. Developed by MS for C#. RxJava is the Java port.

Very steep learning curve.

For writing Asynchronous code, primarily, and that’s what I’ll focus on here.

Instead of pulling data from sources, it’s given to us via a callback.

This callback is of type Observer.

You subscribe to an Observable, providing the Observer callback.

With Reactive Extensions you compose and chain the Observables, providing a single callback - the Observer - which listens to the entire chain of operations. 

This differs from conventional async programming where you call a single function with a callback, then in that callback you call another async function with another callback etc etc. This gets messy fast, and error handling confuses things even more.

An Observer is given data by onNext(), informed about completion via onComplete(), and informed about an error via onError().

Without needing to change any code, you can easily wrap existing code in Observables. Essentially adapting existing classes into something you can chain and compose in the Observable world.

This is all obviously good, but by making lots of things in your codebase Reactive you can combine other data sources (local database), events, other APIs into these chains of Observables. With each chain kind acting like a use case, or routine.

For example, a click event being Reactive, which kicks off a local database query, then an API call.

Because Observable becomes your common interface for all async, or event based code, it’s all compatible.
