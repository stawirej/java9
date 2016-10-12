package language;

class AutoClosableResource implements AutoCloseable {

    private boolean isClosed = false;

    public boolean isClosed() {
        return isClosed;
    }

    @Override
    public void close() throws Exception {
        isClosed = true;
    }
}
