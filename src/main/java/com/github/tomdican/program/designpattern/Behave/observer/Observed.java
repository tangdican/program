package com.github.tomdican.program.designpattern.Behave.observer;

public interface Observed {
        public void registerObserver(Observer o);
        public void removeObserver(Observer o);
        public void notifyObservers();
}
