package com.hotstrip.linux.monitor.common.listener;

/**
 * @author Hotstrip
 * Event Source
 */
public class EventSource {

    protected DataChangedListener dataChangedListener;

    /**
     * registerListener
     * @param dataChangedListener
     */
    public void registerLister(final DataChangedListener dataChangedListener) {
        this.dataChangedListener = dataChangedListener;
    }

    public void dataChanged() {
        this.dataChangedListener.dataChanged(new DataChangedEvent(this));
    }
}
