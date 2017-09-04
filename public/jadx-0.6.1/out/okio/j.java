package okio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: Okio */
public final class j {
    static final Logger a = Logger.getLogger(j.class.getName());

    private j() {
    }

    public static e a(p pVar) {
        return new l(pVar);
    }

    public static d a(o oVar) {
        return new k(oVar);
    }

    private static o a(final OutputStream outputStream, final q qVar) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        } else if (qVar != null) {
            return new o() {
                public void a_(c cVar, long j) throws IOException {
                    r.a(cVar.b, 0, j);
                    while (j > 0) {
                        qVar.g();
                        m mVar = cVar.a;
                        int min = (int) Math.min(j, (long) (mVar.c - mVar.b));
                        outputStream.write(mVar.a, mVar.b, min);
                        mVar.b += min;
                        j -= (long) min;
                        cVar.b -= (long) min;
                        if (mVar.b == mVar.c) {
                            cVar.a = mVar.a();
                            n.a(mVar);
                        }
                    }
                }

                public void flush() throws IOException {
                    outputStream.flush();
                }

                public void close() throws IOException {
                    outputStream.close();
                }

                public q a() {
                    return qVar;
                }

                public String toString() {
                    return "sink(" + outputStream + ")";
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    public static o a(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        q c = c(socket);
        return c.a(a(socket.getOutputStream(), c));
    }

    private static p a(final InputStream inputStream, final q qVar) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        } else if (qVar != null) {
            return new p() {
                public long a(c cVar, long j) throws IOException {
                    if (j < 0) {
                        throw new IllegalArgumentException("byteCount < 0: " + j);
                    } else if (j == 0) {
                        return 0;
                    } else {
                        try {
                            qVar.g();
                            m e = cVar.e(1);
                            int read = inputStream.read(e.a, e.c, (int) Math.min(j, (long) (8192 - e.c)));
                            if (read == -1) {
                                return -1;
                            }
                            e.c += read;
                            cVar.b += (long) read;
                            return (long) read;
                        } catch (AssertionError e2) {
                            if (j.a(e2)) {
                                throw new IOException(e2);
                            }
                            throw e2;
                        }
                    }
                }

                public void close() throws IOException {
                    inputStream.close();
                }

                public q a() {
                    return qVar;
                }

                public String toString() {
                    return "source(" + inputStream + ")";
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    public static p b(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        q c = c(socket);
        return c.a(a(socket.getInputStream(), c));
    }

    private static a c(final Socket socket) {
        return new a() {
            protected IOException a(IOException iOException) {
                IOException socketTimeoutException = new SocketTimeoutException("timeout");
                if (iOException != null) {
                    socketTimeoutException.initCause(iOException);
                }
                return socketTimeoutException;
            }

            protected void a() {
                try {
                    socket.close();
                } catch (Throwable e) {
                    j.a.log(Level.WARNING, "Failed to close timed out socket " + socket, e);
                } catch (AssertionError e2) {
                    if (j.a(e2)) {
                        j.a.log(Level.WARNING, "Failed to close timed out socket " + socket, e2);
                        return;
                    }
                    throw e2;
                }
            }
        };
    }

    static boolean a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }
}
