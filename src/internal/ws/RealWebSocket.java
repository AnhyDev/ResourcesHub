/*     */ package okhttp3.internal.ws;
/*     */ import java.io.IOException;
/*     */ import kotlin.Unit;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.jvm.internal.Ref;
/*     */ import okhttp3.Request;
/*     */ import okhttp3.Response;
/*     */ import okhttp3.internal.Util;
/*     */ import okhttp3.internal.connection.Exchange;
/*     */ import okio.ByteString;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000¶\001\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\003\n\002\020\013\n\000\n\002\030\002\n\002\b\003\n\002\020\016\n\002\b\003\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\b\n\002\b\005\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\002\b\034\030\000 `2\0020\0012\0020\002:\005_`abcB?\022\006\020\003\032\0020\004\022\006\020\005\032\0020\006\022\006\020\007\032\0020\b\022\006\020\t\032\0020\n\022\006\020\013\032\0020\f\022\b\020\r\032\004\030\0010\016\022\006\020\017\032\0020\f¢\006\002\020\020J\026\0202\032\002032\006\0204\032\0020\f2\006\0205\032\00206J\b\0207\032\00203H\026J\037\0208\032\002032\006\0209\032\0020:2\b\020;\032\004\030\0010<H\000¢\006\002\b=J\032\020>\032\0020\0222\006\020?\032\0020%2\b\020@\032\004\030\0010\030H\026J \020>\032\0020\0222\006\020?\032\0020%2\b\020@\032\004\030\0010\0302\006\020A\032\0020\fJ\016\020B\032\002032\006\020C\032\0020DJ\034\020E\032\002032\n\020F\032\0060Gj\002`H2\b\0209\032\004\030\0010:J\026\020I\032\002032\006\020\036\032\0020\0302\006\020*\032\0020+J\006\020J\032\00203J\030\020K\032\002032\006\020?\032\0020%2\006\020@\032\0020\030H\026J\020\020L\032\002032\006\020M\032\0020\030H\026J\020\020L\032\002032\006\020N\032\0020 H\026J\020\020O\032\002032\006\020P\032\0020 H\026J\020\020Q\032\002032\006\020P\032\0020 H\026J\016\020R\032\0020\0222\006\020P\032\0020 J\006\020S\032\0020\022J\b\020!\032\0020\fH\026J\006\020'\032\0020%J\006\020(\032\0020%J\b\020T\032\0020\006H\026J\b\020U\032\00203H\002J\020\020V\032\0020\0222\006\020M\032\0020\030H\026J\020\020V\032\0020\0222\006\020N\032\0020 H\026J\030\020V\032\0020\0222\006\020W\032\0020 2\006\020X\032\0020%H\002J\006\020)\032\0020%J\006\020Y\032\00203J\r\020Z\032\0020\022H\000¢\006\002\b[J\r\020\\\032\00203H\000¢\006\002\b]J\f\020^\032\0020\022*\0020\016H\002R\016\020\021\032\0020\022X\016¢\006\002\n\000R\020\020\023\032\004\030\0010\024X\016¢\006\002\n\000R\016\020\025\032\0020\022X\016¢\006\002\n\000R\020\020\r\032\004\030\0010\016X\016¢\006\002\n\000R\016\020\026\032\0020\022X\016¢\006\002\n\000R\016\020\027\032\0020\030X\004¢\006\002\n\000R\024\020\007\032\0020\bX\004¢\006\b\n\000\032\004\b\031\020\032R\024\020\033\032\b\022\004\022\0020\0350\034X\004¢\006\002\n\000R\016\020\017\032\0020\fX\016¢\006\002\n\000R\020\020\036\032\004\030\0010\030X\016¢\006\002\n\000R\016\020\005\032\0020\006X\004¢\006\002\n\000R\016\020\013\032\0020\fX\004¢\006\002\n\000R\024\020\037\032\b\022\004\022\0020 0\034X\004¢\006\002\n\000R\016\020!\032\0020\fX\016¢\006\002\n\000R\016\020\t\032\0020\nX\004¢\006\002\n\000R\020\020\"\032\004\030\0010#X\016¢\006\002\n\000R\016\020$\032\0020%X\016¢\006\002\n\000R\020\020&\032\004\030\0010\030X\016¢\006\002\n\000R\016\020'\032\0020%X\016¢\006\002\n\000R\016\020(\032\0020%X\016¢\006\002\n\000R\016\020)\032\0020%X\016¢\006\002\n\000R\020\020*\032\004\030\0010+X\016¢\006\002\n\000R\016\020,\032\0020-X\016¢\006\002\n\000R\020\020.\032\004\030\0010/X\016¢\006\002\n\000R\020\0200\032\004\030\00101X\016¢\006\002\n\000¨\006d"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket;", "Lokhttp3/WebSocket;", "Lokhttp3/internal/ws/WebSocketReader$FrameCallback;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "originalRequest", "Lokhttp3/Request;", "listener", "Lokhttp3/WebSocketListener;", "random", "Ljava/util/Random;", "pingIntervalMillis", "", "extensions", "Lokhttp3/internal/ws/WebSocketExtensions;", "minimumDeflateSize", "(Lokhttp3/internal/concurrent/TaskRunner;Lokhttp3/Request;Lokhttp3/WebSocketListener;Ljava/util/Random;JLokhttp3/internal/ws/WebSocketExtensions;J)V", "awaitingPong", "", "call", "Lokhttp3/Call;", "enqueuedClose", "failed", "key", "", "getListener$okhttp", "()Lokhttp3/WebSocketListener;", "messageAndCloseQueue", "Ljava/util/ArrayDeque;", "", "name", "pongQueue", "Lokio/ByteString;", "queueSize", "reader", "Lokhttp3/internal/ws/WebSocketReader;", "receivedCloseCode", "", "receivedCloseReason", "receivedPingCount", "receivedPongCount", "sentPingCount", "streams", "Lokhttp3/internal/ws/RealWebSocket$Streams;", "taskQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "writer", "Lokhttp3/internal/ws/WebSocketWriter;", "writerTask", "Lokhttp3/internal/concurrent/Task;", "awaitTermination", "", "timeout", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "cancel", "checkUpgradeSuccess", "response", "Lokhttp3/Response;", "exchange", "Lokhttp3/internal/connection/Exchange;", "checkUpgradeSuccess$okhttp", "close", "code", "reason", "cancelAfterCloseMillis", "connect", "client", "Lokhttp3/OkHttpClient;", "failWebSocket", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "initReaderAndWriter", "loopReader", "onReadClose", "onReadMessage", "text", "bytes", "onReadPing", "payload", "onReadPong", "pong", "processNextFrame", "request", "runWriter", "send", "data", "formatOpcode", "tearDown", "writeOneFrame", "writeOneFrame$okhttp", "writePingFrame", "writePingFrame$okhttp", "isValid", "Close", "Companion", "Message", "Streams", "WriterTask", "okhttp"}) public final class RealWebSocket implements WebSocket, WebSocketReader.FrameCallback { private final String key; private Call call; private Task writerTask; private WebSocketReader reader; private WebSocketWriter writer; private TaskQueue taskQueue; private String name; private Streams streams; private final ArrayDeque<ByteString> pongQueue; private final ArrayDeque<Object> messageAndCloseQueue; private long queueSize; private boolean enqueuedClose; private int receivedCloseCode; private String receivedCloseReason; private boolean failed; private int sentPingCount; private int receivedPingCount; private int receivedPongCount; private boolean awaitingPong; private final Request originalRequest; @NotNull private final WebSocketListener listener; private final Random random; private final long pingIntervalMillis; private WebSocketExtensions extensions; private long minimumDeflateSize; private static final List<Protocol> ONLY_HTTP1; private static final long MAX_QUEUE_SIZE = 16777216L; private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000L; public static final long DEFAULT_MINIMUM_DEFLATE_SIZE = 1024L; @NotNull public final WebSocketListener getListener$okhttp() { return this.listener; } @NotNull public Request request() { return this.originalRequest; } public synchronized long queueSize() { return this.queueSize; } public void cancel() { Intrinsics.checkNotNull(this.call); this.call.cancel(); } public final void connect(@NotNull OkHttpClient client) { Intrinsics.checkNotNullParameter(client, "client"); if (this.originalRequest.header("Sec-WebSocket-Extensions") != null) {
/*     */       failWebSocket(new ProtocolException("Request header not permitted: 'Sec-WebSocket-Extensions'"), null); return;
/*     */     }  OkHttpClient webSocketClient = client.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build(); Request request = this.originalRequest.newBuilder().header("Upgrade", "websocket").header("Connection", "Upgrade").header("Sec-WebSocket-Key", this.key).header("Sec-WebSocket-Version", "13").header("Sec-WebSocket-Extensions", "permessage-deflate").build(); this.call = (Call)new RealCall(webSocketClient, request, true); Intrinsics.checkNotNull(this.call); this.call.enqueue(new RealWebSocket$connect$1(request)); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000%\n\000\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000*\001\000\b\n\030\0002\0020\001J\030\020\002\032\0020\0032\006\020\004\032\0020\0052\006\020\006\032\0020\007H\026J\030\020\b\032\0020\0032\006\020\004\032\0020\0052\006\020\t\032\0020\nH\026¨\006\013"}, d2 = {"okhttp3/internal/ws/RealWebSocket$connect$1", "Lokhttp3/Callback;", "onFailure", "", "call", "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "okhttp"}) public static final class RealWebSocket$connect$1 implements Callback { RealWebSocket$connect$1(Request $captured_local_variable$1) {} public void onResponse(@NotNull Call call, @NotNull Response response) { Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(response, "response"); Exchange exchange = response.exchange(); RealWebSocket.Streams streams = null; try {
/*     */         RealWebSocket.this.checkUpgradeSuccess$okhttp(response, exchange); Intrinsics.checkNotNull(exchange); streams = exchange.newWebSocketStreams();
/*     */       } catch (IOException e) {
/*     */         if (exchange != null) {
/*     */           exchange.webSocketUpgradeFailed();
/*     */         } else {
/*     */         
/*     */         }  RealWebSocket.this.failWebSocket(e, response); Util.closeQuietly((Closeable)response); return;
/*     */       }  WebSocketExtensions extensions = WebSocketExtensions.Companion.parse(response.headers()); RealWebSocket.this.extensions = extensions; if (!RealWebSocket.this.isValid(extensions)) {
/*     */         RealWebSocket realWebSocket = RealWebSocket.this; boolean bool = false; synchronized (false) {
/*     */           int $i$a$-synchronized-RealWebSocket$connect$1$onResponse$1 = 0; RealWebSocket.this.messageAndCloseQueue.clear(); null = RealWebSocket.this.close(1010, "unexpected Sec-WebSocket-Extensions in response header"); /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/ws/RealWebSocket}, name=null} */
/*     */         } 
/*     */       }  try {
/*     */         String name = Util.okHttpName + " WebSocket " + this.$request.url().redact(); RealWebSocket.this.initReaderAndWriter(name, streams); RealWebSocket.this.getListener$okhttp().onOpen(RealWebSocket.this, response); RealWebSocket.this.loopReader();
/*     */       } catch (Exception e) {
/*     */         RealWebSocket.this.failWebSocket(e, null);
/*     */       }  } public void onFailure(@NotNull Call call, @NotNull IOException e) { Intrinsics.checkNotNullParameter(call, "call"); Intrinsics.checkNotNullParameter(e, "e"); RealWebSocket.this.failWebSocket(e, null); } } private final boolean isValid(WebSocketExtensions $this$isValid) { if ($this$isValid.unknownValues)
/*     */       return false;  if ($this$isValid.clientMaxWindowBits != null)
/*     */       return false;  if ($this$isValid.serverMaxWindowBits != null) {
/*     */       int i = $this$isValid.serverMaxWindowBits.intValue(); if (8 <= i) {
/*     */         if (15 < i)
/*     */           return false; 
/*     */       } else {
/*     */         15; return false;
/*     */       } 
/*     */     }  return true; } public final void checkUpgradeSuccess$okhttp(@NotNull Response response, @Nullable Exchange exchange) throws IOException { Intrinsics.checkNotNullParameter(response, "response"); if (response.code() != 101)
/*     */       throw (Throwable)new ProtocolException("Expected HTTP 101 response but was '" + response.code() + ' ' + response.message() + '\'');  String headerConnection = Response.header$default(response, "Connection", null, 2, null); if (!StringsKt.equals("Upgrade", headerConnection, true))
/*     */       throw (Throwable)new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + headerConnection + '\'');  String headerUpgrade = Response.header$default(response, "Upgrade", null, 2, null); if (!StringsKt.equals("websocket", headerUpgrade, true))
/*     */       throw (Throwable)new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + headerUpgrade + '\'');  String headerAccept = Response.header$default(response, "Sec-WebSocket-Accept", null, 2, null); String acceptExpected = ByteString.Companion.encodeUtf8(this.key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").sha1().base64(); if ((Intrinsics.areEqual(acceptExpected, headerAccept) ^ true) != 0)
/*     */       throw (Throwable)new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + acceptExpected + "' but was '" + headerAccept + '\'');  if (exchange == null)
/*     */       throw (Throwable)new ProtocolException("Web Socket exchange missing: bad interceptor?");  } public final void initReaderAndWriter(@NotNull String name, @NotNull Streams streams) throws IOException { Intrinsics.checkNotNullParameter(name, "name"); Intrinsics.checkNotNullParameter(streams, "streams"); Intrinsics.checkNotNull(this.extensions); WebSocketExtensions extensions = this.extensions; RealWebSocket realWebSocket = this; boolean bool = false; synchronized (false) {
/*     */       int $i$a$-synchronized-RealWebSocket$initReaderAndWriter$1 = 0; this.name = name; this.streams = streams; this.writer = new WebSocketWriter(streams.getClient(), streams.getSink(), this.random, extensions.perMessageDeflate, extensions.noContextTakeover(streams.getClient()), this.minimumDeflateSize); this.writerTask = new WriterTask(); if (this.pingIntervalMillis != 0L) {
/*     */         long pingIntervalNanos = TimeUnit.MILLISECONDS.toNanos(this.pingIntervalMillis); TaskQueue taskQueue = this.taskQueue; String name$iv = name + " ping"; int $i$f$schedule = 0; taskQueue.schedule(new RealWebSocket$initReaderAndWriter$$inlined$synchronized$lambda$1(name$iv, name$iv, pingIntervalNanos, this, name, streams, extensions), pingIntervalNanos);
/*     */       }  ArrayDeque<Object> arrayDeque = this.messageAndCloseQueue; boolean bool1 = false; if (!arrayDeque.isEmpty())
/*     */         runWriter();  Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/ws/RealWebSocket}, name=null} */
/*     */     }  this.reader = new WebSocketReader(streams.getClient(), streams.getSource(), this, extensions.perMessageDeflate, extensions.noContextTakeover(!streams.getClient())); } public final void loopReader() throws IOException { while (this.receivedCloseCode == -1) {
/*     */       Intrinsics.checkNotNull(this.reader);
/*     */       this.reader.processNextFrame();
/*  53 */     }  } public RealWebSocket(@NotNull TaskRunner taskRunner, @NotNull Request originalRequest, @NotNull WebSocketListener listener, @NotNull Random random, long pingIntervalMillis, @Nullable WebSocketExtensions extensions, long minimumDeflateSize) { this.originalRequest = originalRequest; this.listener = listener; this.random = random; this.pingIntervalMillis = pingIntervalMillis; this.extensions = extensions; this.minimumDeflateSize = minimumDeflateSize;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  85 */     this.taskQueue = taskRunner.newQueue();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     this.pongQueue = new ArrayDeque<>();
/*     */ 
/*     */     
/* 101 */     this.messageAndCloseQueue = new ArrayDeque();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     this.receivedCloseCode = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 131 */     boolean bool = Intrinsics.areEqual("GET", this.originalRequest.method()); boolean bool1 = false, bool2 = false; if (!bool) { int $i$a$-require-RealWebSocket$1 = 0; String str = 
/* 132 */         "Request must be GET: " + this.originalRequest.method();
/*     */       throw (Throwable)new IllegalArgumentException(str.toString()); }
/*     */     
/* 135 */     byte[] arrayOfByte1 = new byte[16]; bool1 = false; bool2 = false; byte[] arrayOfByte2 = arrayOfByte1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 659 */     ByteString.Companion companion = ByteString.Companion; RealWebSocket realWebSocket = this; int $i$a$-apply-RealWebSocket$2 = 0; this.random.nextBytes(arrayOfByte2); Unit unit = Unit.INSTANCE; realWebSocket.key = ByteString.Companion.of$default(companion, arrayOfByte1, 0, 0, 3, null).base64(); } public final boolean processNextFrame() throws IOException { boolean bool; try { Intrinsics.checkNotNull(this.reader); this.reader.processNextFrame(); bool = (this.receivedCloseCode == -1) ? true : false; } catch (Exception e) { failWebSocket(e, null); bool = false; }  return bool; } public final void awaitTermination(long timeout, @NotNull TimeUnit timeUnit) throws InterruptedException { Intrinsics.checkNotNullParameter(timeUnit, "timeUnit"); this.taskQueue.idleLatch().await(timeout, timeUnit); } public final void tearDown() throws InterruptedException { this.taskQueue.shutdown(); this.taskQueue.idleLatch().await(10L, TimeUnit.SECONDS); } public final synchronized int sentPingCount() { return this.sentPingCount; } public final synchronized int receivedPingCount() { return this.receivedPingCount; } public final synchronized int receivedPongCount() { return this.receivedPongCount; } public void onReadMessage(@NotNull String text) throws IOException { Intrinsics.checkNotNullParameter(text, "text"); this.listener.onMessage(this, text); } public void onReadMessage(@NotNull ByteString bytes) throws IOException { Intrinsics.checkNotNullParameter(bytes, "bytes"); this.listener.onMessage(this, bytes); } public synchronized void onReadPing(@NotNull ByteString payload) { Intrinsics.checkNotNullParameter(payload, "payload"); if (this.failed || (this.enqueuedClose && this.messageAndCloseQueue.isEmpty())) return;  this.pongQueue.add(payload); runWriter(); int i; this.receivedPingCount = (i = this.receivedPingCount) + 1; } public synchronized void onReadPong(@NotNull ByteString payload) { Intrinsics.checkNotNullParameter(payload, "payload"); int i; this.receivedPongCount = (i = this.receivedPongCount) + 1; this.awaitingPong = false; } public void onReadClose(int code, @NotNull String reason) { Intrinsics.checkNotNullParameter(reason, "reason"); boolean bool1 = (code != -1) ? true : false, bool2 = false, bool3 = false; bool3 = false; boolean bool4 = false; if (!bool1) { boolean bool = false; String str = "Failed requirement."; throw (Throwable)new IllegalArgumentException(str.toString()); }  Object toClose = null; Object readerToClose = null; Object writerToClose = null; null = this; boolean bool5 = false; synchronized (false) { int $i$a$-synchronized-RealWebSocket$onReadClose$1 = 0; boolean bool6 = (this.receivedCloseCode == -1) ? true : false, bool7 = false, bool8 = false; if (!bool6) { int $i$a$-check-RealWebSocket$onReadClose$1$1 = 0; String str = "already closed"; throw (Throwable)new IllegalStateException(str.toString()); }  this.receivedCloseCode = code; this.receivedCloseReason = reason; if (this.enqueuedClose && this.messageAndCloseQueue.isEmpty()) { toClose = this.streams; this.streams = (Streams)null; readerToClose = this.reader; this.reader = (WebSocketReader)null; writerToClose = this.writer; this.writer = (WebSocketWriter)null; this.taskQueue.shutdown(); }  Unit unit = Unit.INSTANCE; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/ws/RealWebSocket}, name=null} */ }  try { this.listener.onClosing(this, code, reason); if (toClose != null) this.listener.onClosed(this, code, reason);  } finally { if (toClose != null) { Util.closeQuietly((Closeable)toClose); } else {  }  if (readerToClose != null) { Util.closeQuietly((Closeable)readerToClose); } else {  }  if (writerToClose != null) { Util.closeQuietly((Closeable)writerToClose); } else {  }  }  } public boolean send(@NotNull String text) { Intrinsics.checkNotNullParameter(text, "text"); return send(ByteString.Companion.encodeUtf8(text), 1); } public boolean send(@NotNull ByteString bytes) { Intrinsics.checkNotNullParameter(bytes, "bytes"); return send(bytes, 2); } private final synchronized boolean send(ByteString data, int formatOpcode) { if (this.failed || this.enqueuedClose) return false;  if (this.queueSize + data.size() > 16777216L) { close(1001, null); return false; }  this.queueSize += data.size(); this.messageAndCloseQueue.add(new Message(formatOpcode, data)); runWriter(); return true; } public final synchronized boolean pong(@NotNull ByteString payload) { Intrinsics.checkNotNullParameter(payload, "payload"); if (this.failed || (this.enqueuedClose && this.messageAndCloseQueue.isEmpty())) return false;  this.pongQueue.add(payload); runWriter(); return true; } public boolean close(int code, @Nullable String reason) { return close(code, reason, 60000L); } public final synchronized boolean close(int code, @Nullable String reason, long cancelAfterCloseMillis) { WebSocketProtocol.INSTANCE.validateCloseCode(code); ByteString reasonBytes = (ByteString)null; if (reason != null) { reasonBytes = ByteString.Companion.encodeUtf8(reason); boolean bool1 = (reasonBytes.size() <= 123L) ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-require-RealWebSocket$close$1 = 0; String str = "reason.size() > 123: " + reason; throw (Throwable)new IllegalArgumentException(str.toString()); }  }  if (this.failed || this.enqueuedClose) return false;  this.enqueuedClose = true; this.messageAndCloseQueue.add(new Close(code, reasonBytes, cancelAfterCloseMillis)); runWriter(); return true; }
/* 660 */   private final void runWriter() { Object $this$assertThreadHoldsLock$iv = this; int $i$f$assertThreadHoldsLock = 0; if (Util.assertionsEnabled && !Thread.holdsLock($this$assertThreadHoldsLock$iv))
/* 661 */     { Intrinsics.checkNotNullExpressionValue(Thread.currentThread(), "Thread.currentThread()"); throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + $this$assertThreadHoldsLock$iv); }  Task writerTask = this.writerTask; if (writerTask != null)
/*     */       TaskQueue.schedule$default(this.taskQueue, writerTask, 0L, 2, null);  } public final boolean writeOneFrame$okhttp() throws IOException { WebSocketWriter writer = null; ByteString pong = null; Ref.ObjectRef objectRef1 = new Ref.ObjectRef(); objectRef1.element = null; Ref.IntRef intRef = new Ref.IntRef(); intRef.element = -1; Ref.ObjectRef objectRef2 = new Ref.ObjectRef(); objectRef2.element = null; Ref.ObjectRef objectRef3 = new Ref.ObjectRef(); objectRef3.element = null; Ref.ObjectRef objectRef4 = new Ref.ObjectRef(); objectRef4.element = null; Ref.ObjectRef objectRef5 = new Ref.ObjectRef(); objectRef5.element = null; RealWebSocket realWebSocket = this; boolean bool = false; synchronized (false) { int $i$a$-synchronized-RealWebSocket$writeOneFrame$1 = 0; if (this.failed) { boolean bool1 = false; /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/ws/RealWebSocket}, name=null} */ return bool1; }  writer = this.writer; pong = this.pongQueue.poll(); if (pong == null) { objectRef1.element = this.messageAndCloseQueue.poll(); if (objectRef1.element instanceof Close) { intRef.element = this.receivedCloseCode; objectRef2.element = this.receivedCloseReason; if (intRef.element != -1) { objectRef3.element = this.streams; this.streams = (Streams)null; objectRef4.element = this.reader; this.reader = (WebSocketReader)null; objectRef5.element = this.writer; this.writer = (WebSocketWriter)null; this.taskQueue.shutdown(); }
/*     */           else { if (objectRef1.element == null)
/* 664 */               throw new NullPointerException("null cannot be cast to non-null type okhttp3.internal.ws.RealWebSocket.Close");  long cancelAfterCloseMillis = ((Close)objectRef1.element).getCancelAfterCloseMillis(); TaskQueue taskQueue = this.taskQueue; String str = this.name + " cancel"; long delayNanos$iv = TimeUnit.MILLISECONDS.toNanos(cancelAfterCloseMillis); boolean cancelable$iv = true; int $i$f$execute = 0;
/*     */ 
/*     */             
/* 667 */             taskQueue.schedule(new RealWebSocket$writeOneFrame$$inlined$synchronized$lambda$1(str, cancelable$iv, str, cancelable$iv, this, writer, pong, objectRef1, intRef, objectRef2, objectRef3, objectRef4, objectRef5), 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 672 */                 delayNanos$iv); }
/*     */            }
/*     */         else if (objectRef1.element == null)
/*     */         { boolean bool1 = false;
/*     */           /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/ws/RealWebSocket}, name=null} */
/*     */           return bool1; }
/*     */          }
/*     */       
/*     */       Unit unit = Unit.INSTANCE;
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/ws/RealWebSocket}, name=null} */ }
/*     */     
/*     */     try {
/*     */       if (pong != null) {
/*     */         Intrinsics.checkNotNull(writer);
/*     */         writer.writePong(pong);
/*     */       } else if (objectRef1.element instanceof Message) {
/*     */         if (objectRef1.element == null)
/*     */           throw new NullPointerException("null cannot be cast to non-null type okhttp3.internal.ws.RealWebSocket.Message"); 
/*     */         Message message = (Message)objectRef1.element;
/*     */         Intrinsics.checkNotNull(writer);
/*     */         writer.writeMessageFrame(message.getFormatOpcode(), message.getData());
/*     */         RealWebSocket realWebSocket1 = this;
/*     */         boolean bool2 = false;
/*     */         synchronized (false) {
/*     */           int $i$a$-synchronized-RealWebSocket$writeOneFrame$2 = 0;
/*     */           this.queueSize -= message.getData().size();
/*     */           Unit unit = Unit.INSTANCE;
/*     */           /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/ws/RealWebSocket}, name=null} */
/*     */         } 
/*     */       } else if (objectRef1.element instanceof Close) {
/*     */         if (objectRef1.element == null)
/*     */           throw new NullPointerException("null cannot be cast to non-null type okhttp3.internal.ws.RealWebSocket.Close"); 
/*     */         Close close = (Close)objectRef1.element;
/*     */         Intrinsics.checkNotNull(writer);
/*     */         writer.writeClose(close.getCode(), close.getReason());
/*     */         if ((Streams)objectRef3.element != null) {
/*     */           Intrinsics.checkNotNull(objectRef2.element);
/*     */           this.listener.onClosed(this, intRef.element, (String)objectRef2.element);
/*     */         } 
/*     */       } else {
/*     */         throw new AssertionError();
/*     */       } 
/*     */       boolean bool1 = true;
/*     */       if ((Streams)objectRef3.element != null) {
/*     */         Util.closeQuietly((Streams)objectRef3.element);
/*     */       } else {
/*     */         (Streams)objectRef3.element;
/*     */       } 
/*     */       if ((WebSocketReader)objectRef4.element != null) {
/*     */         Util.closeQuietly((WebSocketReader)objectRef4.element);
/*     */       } else {
/*     */         (WebSocketReader)objectRef4.element;
/*     */       } 
/*     */       if ((WebSocketWriter)objectRef5.element != null) {
/*     */         Util.closeQuietly((WebSocketWriter)objectRef5.element);
/*     */       } else {
/*     */         (WebSocketWriter)objectRef5.element;
/*     */       } 
/*     */       return bool1;
/*     */     } finally {}
/*     */     if ((Streams)objectRef3.element != null) {
/*     */       Util.closeQuietly((Streams)objectRef3.element);
/*     */     } else {
/*     */       (Streams)objectRef3.element;
/*     */     } 
/*     */     if ((WebSocketReader)objectRef4.element != null) {
/*     */       Util.closeQuietly((WebSocketReader)objectRef4.element);
/*     */     } else {
/*     */       (WebSocketReader)objectRef4.element;
/*     */     } 
/*     */     if ((WebSocketWriter)objectRef5.element != null) {
/*     */       Util.closeQuietly((WebSocketWriter)objectRef5.element);
/*     */     } else {
/*     */       (WebSocketWriter)objectRef5.element;
/*     */     } 
/*     */     throw realWebSocket; }
/*     */ 
/*     */   
/*     */   public final void writePingFrame$okhttp() {
/*     */     WebSocketWriter writer = null;
/*     */     int failedPing = 0;
/*     */     RealWebSocket realWebSocket = this;
/*     */     boolean bool = false;
/*     */     synchronized (false) {
/*     */       int $i$a$-synchronized-RealWebSocket$writePingFrame$1 = 0;
/*     */       if (this.failed) {
/*     */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/ws/RealWebSocket}, name=null} */
/*     */         return;
/*     */       } 
/*     */       if (this.writer != null) {
/*     */         writer = this.writer;
/*     */         failedPing = this.awaitingPong ? this.sentPingCount : -1;
/*     */         int i;
/*     */         this.sentPingCount = (i = this.sentPingCount) + 1;
/*     */         this.awaitingPong = true;
/*     */         Unit unit = Unit.INSTANCE;
/*     */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/ws/RealWebSocket}, name=null} */
/*     */       } 
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/ws/RealWebSocket}, name=null} */
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void failWebSocket(@NotNull Exception e, @Nullable Response response) {
/*     */     Intrinsics.checkNotNullParameter(e, "e");
/*     */     Streams streamsToClose = null;
/*     */     WebSocketReader readerToClose = null;
/*     */     WebSocketWriter writerToClose = null;
/*     */     null = this;
/*     */     boolean bool = false;
/*     */     synchronized (false) {
/*     */       int $i$a$-synchronized-RealWebSocket$failWebSocket$1 = 0;
/*     */       if (this.failed) {
/*     */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/ws/RealWebSocket}, name=null} */
/*     */         return;
/*     */       } 
/*     */       this.failed = true;
/*     */       streamsToClose = this.streams;
/*     */       this.streams = (Streams)null;
/*     */       readerToClose = this.reader;
/*     */       this.reader = (WebSocketReader)null;
/*     */       writerToClose = this.writer;
/*     */       this.writer = (WebSocketWriter)null;
/*     */       this.taskQueue.shutdown();
/*     */       Unit unit = Unit.INSTANCE;
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/ws/RealWebSocket}, name=null} */
/*     */     } 
/*     */     try {
/*     */       this.listener.onFailure(this, e, response);
/*     */     } finally {
/*     */       if (streamsToClose != null) {
/*     */         Util.closeQuietly(streamsToClose);
/*     */       } else {
/*     */       
/*     */       } 
/*     */       if (readerToClose != null) {
/*     */         Util.closeQuietly(readerToClose);
/*     */       } else {
/*     */       
/*     */       } 
/*     */       if (writerToClose != null) {
/*     */         Util.closeQuietly(writerToClose);
/*     */       } else {
/*     */       
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\030\n\002\030\002\n\002\020\000\n\000\n\002\020\b\n\000\n\002\030\002\n\002\b\006\b\000\030\0002\0020\001B\025\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005¢\006\002\020\006R\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\007\020\bR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\t\020\n¨\006\013"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Message;", "", "formatOpcode", "", "data", "Lokio/ByteString;", "(ILokio/ByteString;)V", "getData", "()Lokio/ByteString;", "getFormatOpcode", "()I", "okhttp"})
/*     */   public static final class Message {
/*     */     private final int formatOpcode;
/*     */     @NotNull
/*     */     private final ByteString data;
/*     */     
/*     */     public Message(int formatOpcode, @NotNull ByteString data) {
/*     */       this.formatOpcode = formatOpcode;
/*     */       this.data = data;
/*     */     }
/*     */     
/*     */     public final int getFormatOpcode() {
/*     */       return this.formatOpcode;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final ByteString getData() {
/*     */       return this.data;
/*     */     }
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\036\n\002\030\002\n\002\020\000\n\000\n\002\020\b\n\000\n\002\030\002\n\000\n\002\020\t\n\002\b\b\b\000\030\0002\0020\001B\037\022\006\020\002\032\0020\003\022\b\020\004\032\004\030\0010\005\022\006\020\006\032\0020\007¢\006\002\020\bR\021\020\006\032\0020\007¢\006\b\n\000\032\004\b\t\020\nR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\013\020\fR\023\020\004\032\004\030\0010\005¢\006\b\n\000\032\004\b\r\020\016¨\006\017"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Close;", "", "code", "", "reason", "Lokio/ByteString;", "cancelAfterCloseMillis", "", "(ILokio/ByteString;J)V", "getCancelAfterCloseMillis", "()J", "getCode", "()I", "getReason", "()Lokio/ByteString;", "okhttp"})
/*     */   public static final class Close {
/*     */     private final int code;
/*     */     @Nullable
/*     */     private final ByteString reason;
/*     */     private final long cancelAfterCloseMillis;
/*     */     
/*     */     public Close(int code, @Nullable ByteString reason, long cancelAfterCloseMillis) {
/*     */       this.code = code;
/*     */       this.reason = reason;
/*     */       this.cancelAfterCloseMillis = cancelAfterCloseMillis;
/*     */     }
/*     */     
/*     */     public final int getCode() {
/*     */       return this.code;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public final ByteString getReason() {
/*     */       return this.reason;
/*     */     }
/*     */     
/*     */     public final long getCancelAfterCloseMillis() {
/*     */       return this.cancelAfterCloseMillis;
/*     */     }
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\036\n\002\030\002\n\002\030\002\n\000\n\002\020\013\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\b\b&\030\0002\0020\001B\035\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007¢\006\002\020\bR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\t\020\nR\021\020\006\032\0020\007¢\006\b\n\000\032\004\b\013\020\fR\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\r\020\016¨\006\017"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Streams;", "Ljava/io/Closeable;", "client", "", "source", "Lokio/BufferedSource;", "sink", "Lokio/BufferedSink;", "(ZLokio/BufferedSource;Lokio/BufferedSink;)V", "getClient", "()Z", "getSink", "()Lokio/BufferedSink;", "getSource", "()Lokio/BufferedSource;", "okhttp"})
/*     */   public static abstract class Streams implements Closeable {
/*     */     private final boolean client;
/*     */     @NotNull
/*     */     private final BufferedSource source;
/*     */     @NotNull
/*     */     private final BufferedSink sink;
/*     */     
/*     */     public Streams(boolean client, @NotNull BufferedSource source, @NotNull BufferedSink sink) {
/*     */       this.client = client;
/*     */       this.source = source;
/*     */       this.sink = sink;
/*     */     }
/*     */     
/*     */     public final boolean getClient() {
/*     */       return this.client;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final BufferedSource getSource() {
/*     */       return this.source;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final BufferedSink getSink() {
/*     */       return this.sink;
/*     */     }
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\022\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\t\n\000\b\004\030\0002\0020\001B\005¢\006\002\020\002J\b\020\003\032\0020\004H\026¨\006\005"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$WriterTask;", "Lokhttp3/internal/concurrent/Task;", "(Lokhttp3/internal/ws/RealWebSocket;)V", "runOnce", "", "okhttp"})
/*     */   private final class WriterTask extends Task {
/*     */     public WriterTask() {
/*     */       super(RealWebSocket.this.name + " writer", false, 2, null);
/*     */     }
/*     */     
/*     */     public long runOnce() {
/*     */       try {
/*     */         if (RealWebSocket.this.writeOneFrame$okhttp())
/*     */           return 0L; 
/*     */       } catch (IOException e) {
/*     */         RealWebSocket.this.failWebSocket(e, null);
/*     */       } 
/*     */       return -1L;
/*     */     }
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\036\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\t\n\002\b\003\n\002\020 \n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\016\020\003\032\0020\004XT¢\006\002\n\000R\016\020\005\032\0020\004XT¢\006\002\n\000R\016\020\006\032\0020\004XT¢\006\002\n\000R\024\020\007\032\b\022\004\022\0020\t0\bX\004¢\006\002\n\000¨\006\n"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Companion;", "", "()V", "CANCEL_AFTER_CLOSE_MILLIS", "", "DEFAULT_MINIMUM_DEFLATE_SIZE", "MAX_QUEUE_SIZE", "ONLY_HTTP1", "", "Lokhttp3/Protocol;", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */   }
/*     */   
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   static {
/*     */     ONLY_HTTP1 = CollectionsKt.listOf(Protocol.HTTP_1_1);
/*     */   } }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/ws/RealWebSocket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */