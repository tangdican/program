package com.github.tomdican.program.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.ISO_8859_1;

    private static final int BUFFER_SIZE = 1024;

    private int port = 8081;

    public NIOServer(int port) {
        this.port = port;
    }

    public NIOServer() {
    }

    public void start() {
        ServerSocketChannel ssc = null;
        try {
            ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ssc.bind(new InetSocketAddress(this.port));
            Selector sel = Selector.open();
            ssc.register(sel, SelectionKey.OP_ACCEPT);
            while (true) {
                Set<SelectionKey> keySet = null;
                try {
                    sel.select();
                    keySet = sel.selectedKeys();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }

                for (Iterator<SelectionKey> it = keySet.iterator(); it.hasNext();) {
                    SelectionKey sKey = it.next();
                    it.remove();
                    try {
                        if (sKey.isAcceptable()) {
                            ServerSocketChannel serChannel = (ServerSocketChannel) sKey.channel();
                            SocketChannel clientChannel = serChannel.accept();
                            clientChannel.configureBlocking(false);

                            SelectionKey k2 = clientChannel.register(sel, SelectionKey.OP_READ);
                            k2.attach(ByteBuffer.allocate(BUFFER_SIZE));

                        } else if (sKey.isWritable()) {
                            SocketChannel clientChannel = (SocketChannel) sKey.channel();
                            ByteBuffer[] bfs = (ByteBuffer[]) sKey.attachment();
                            if (bfs[bfs.length - 1].hasRemaining()) {
                                clientChannel.write(bfs);
                            } else {
                                clientChannel.close();

                            }

                        } else if (sKey.isReadable()) {
                            SocketChannel clientChannel = (SocketChannel) sKey.channel();
                            ByteBuffer bf = (ByteBuffer) sKey.attachment();
                            String msg = "";
                            boolean clientEnd = false;
                            if (bf.hasRemaining()) {
                                int len = clientChannel.read(bf);
                                if (len != -1 && bf.position() > 1) {
                                    char lastChar = (char) bf.get(bf.position() - 1);
                                    char last2Char = (char) bf.get(bf.position() - 2);
                                    if (String.valueOf(new char[] { last2Char, lastChar }).equals("\r\n")) {
                                        System.out.println("client inupt end.");
                                        clientEnd = true;
                                    }
                                }

                                if (len == -1) {
                                    System.out.println("client closed.");
                                    clientEnd = true;
                                }

                            } else {
                                System.out.println("buff is full.");
                                msg = "You can only enter " + BUFFER_SIZE + " chars\r\n";
                                clientEnd = true;
                            }

                            if (clientEnd) {
                                ByteBuffer[] att = processInput(bf, msg);
                                clientChannel.register(sel, SelectionKey.OP_WRITE, att);
                            }
                        }
                    } catch (Exception e) {
                        sKey.cancel();
                        e.printStackTrace();
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ssc != null) {
                try {
                    ssc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private ByteBuffer[] processInput(ByteBuffer bf, String msg) throws Exception {
        bf.flip();
        ByteBuffer promptMsg = ByteBuffer.wrap((msg + "You just input:\r\n").getBytes(DEFAULT_CHARSET));

        String inputMsg = new String(bf.array(), bf.position(), bf.limit(), DEFAULT_CHARSET).trim();

        ByteBuffer[] att = new ByteBuffer[] { promptMsg, bf };

        if (inputMsg.indexOf("get file:") >= 0) {
            String fileName = inputMsg.substring("get file:".length()).trim();
            System.out.println("fileName=" + fileName);
            URL fileURL = this.getClass().getClassLoader().getResource(fileName);
            if (fileURL != null) {
                Path path = Paths.get(fileURL.toURI());
                System.out.println(path);

                ByteBuffer fileData = ByteBuffer.wrap(Files.readAllBytes(path));
                ByteBuffer info = ByteBuffer
                        .wrap(("The content of file " + fileName + ":\r\n").getBytes(DEFAULT_CHARSET));
                att = new ByteBuffer[] { promptMsg, bf, info, fileData };

            } else {
                String errMsg = "fileName: " + fileName + " not found in the classpath.";
                System.out.println(errMsg);

                att = new ByteBuffer[] { promptMsg, bf, ByteBuffer.wrap(errMsg.getBytes(DEFAULT_CHARSET)) };

            }

        }
        return att;
    }

    public static void main(String[] args) {
        new NIOServer().start();
    }

}


