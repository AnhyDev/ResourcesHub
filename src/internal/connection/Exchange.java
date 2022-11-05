/*     */ package okhttp3.internal.connection;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.ProtocolException;
/*     */ import java.net.SocketException;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okhttp3.EventListener;
/*     */ import okhttp3.Headers;
/*     */ import okhttp3.Request;
/*     */ import okhttp3.Response;
/*     */ import okhttp3.ResponseBody;
/*     */ import okhttp3.internal.http.ExchangeCodec;
/*     */ import okhttp3.internal.http.RealResponseBody;
/*     */ import okhttp3.internal.ws.RealWebSocket;
/*     */ import okio.Buffer;
/*     */ import okio.ForwardingSink;
/*     */ import okio.ForwardingSource;
/*     */ import okio.Okio;
/*     */ import okio.Sink;
/*     */ import okio.Source;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000z\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\007\n\002\020\013\n\002\b\007\n\002\030\002\n\000\n\002\020\t\n\002\b\005\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\030\0002\0020\001:\002ABB%\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\007\022\006\020\b\032\0020\t¢\006\002\020\nJ7\020\034\032\002H\035\"\n\b\000\020\035*\004\030\0010\0362\006\020\037\032\0020 2\006\020!\032\0020\0262\006\020\"\032\0020\0262\006\020#\032\002H\035¢\006\002\020$J\006\020%\032\0020&J\026\020'\032\0020(2\006\020)\032\0020*2\006\020+\032\0020\026J\006\020,\032\0020&J\006\020-\032\0020&J\006\020.\032\0020&J\006\020/\032\00200J\006\0201\032\0020&J\006\0202\032\0020&J\016\0203\032\002042\006\0205\032\00206J\020\0207\032\004\030\001082\006\0209\032\0020\026J\016\020:\032\0020&2\006\0205\032\00206J\006\020;\032\0020&J\020\020<\032\0020&2\006\020#\032\0020\036H\002J\006\020=\032\0020>J\006\020?\032\0020&J\016\020@\032\0020&2\006\020)\032\0020*R\024\020\002\032\0020\003X\004¢\006\b\n\000\032\004\b\013\020\fR\016\020\b\032\0020\tX\004¢\006\002\n\000R\024\020\r\032\0020\016X\004¢\006\b\n\000\032\004\b\017\020\020R\024\020\004\032\0020\005X\004¢\006\b\n\000\032\004\b\021\020\022R\024\020\006\032\0020\007X\004¢\006\b\n\000\032\004\b\023\020\024R\024\020\025\032\0020\0268@X\004¢\006\006\032\004\b\027\020\030R\036\020\032\032\0020\0262\006\020\031\032\0020\026@BX\016¢\006\b\n\000\032\004\b\033\020\030¨\006C"}, d2 = {"Lokhttp3/internal/connection/Exchange;", "", "call", "Lokhttp3/internal/connection/RealCall;", "eventListener", "Lokhttp3/EventListener;", "finder", "Lokhttp3/internal/connection/ExchangeFinder;", "codec", "Lokhttp3/internal/http/ExchangeCodec;", "(Lokhttp3/internal/connection/RealCall;Lokhttp3/EventListener;Lokhttp3/internal/connection/ExchangeFinder;Lokhttp3/internal/http/ExchangeCodec;)V", "getCall$okhttp", "()Lokhttp3/internal/connection/RealCall;", "connection", "Lokhttp3/internal/connection/RealConnection;", "getConnection$okhttp", "()Lokhttp3/internal/connection/RealConnection;", "getEventListener$okhttp", "()Lokhttp3/EventListener;", "getFinder$okhttp", "()Lokhttp3/internal/connection/ExchangeFinder;", "isCoalescedConnection", "", "isCoalescedConnection$okhttp", "()Z", "<set-?>", "isDuplex", "isDuplex$okhttp", "bodyComplete", "E", "Ljava/io/IOException;", "bytesRead", "", "responseDone", "requestDone", "e", "(JZZLjava/io/IOException;)Ljava/io/IOException;", "cancel", "", "createRequestBody", "Lokio/Sink;", "request", "Lokhttp3/Request;", "duplex", "detachWithViolence", "finishRequest", "flushRequest", "newWebSocketStreams", "Lokhttp3/internal/ws/RealWebSocket$Streams;", "noNewExchangesOnConnection", "noRequestBody", "openResponseBody", "Lokhttp3/ResponseBody;", "response", "Lokhttp3/Response;", "readResponseHeaders", "Lokhttp3/Response$Builder;", "expectContinue", "responseHeadersEnd", "responseHeadersStart", "trackFailure", "trailers", "Lokhttp3/Headers;", "webSocketUpgradeFailed", "writeRequestHeaders", "RequestBodySink", "ResponseBodySource", "okhttp"})
/*     */ public final class Exchange
/*     */ {
/*     */   private boolean isDuplex;
/*     */   @NotNull
/*     */   private final RealConnection connection;
/*     */   @NotNull
/*     */   private final RealCall call;
/*     */   @NotNull
/*     */   private final EventListener eventListener;
/*     */   @NotNull
/*     */   private final ExchangeFinder finder;
/*     */   private final ExchangeCodec codec;
/*     */   
/*     */   public Exchange(@NotNull RealCall call, @NotNull EventListener eventListener, @NotNull ExchangeFinder finder, @NotNull ExchangeCodec codec) {
/*  40 */     this.call = call; this.eventListener = eventListener; this.finder = finder; this.codec = codec;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  50 */     this.connection = this.codec.getConnection(); } @NotNull public final RealCall getCall$okhttp() { return this.call; } @NotNull public final EventListener getEventListener$okhttp() { return this.eventListener; } @NotNull public final ExchangeFinder getFinder$okhttp() { return this.finder; } public final boolean isDuplex$okhttp() { return this.isDuplex; } @NotNull public final RealConnection getConnection$okhttp() { return this.connection; }
/*     */   
/*     */   public final boolean isCoalescedConnection$okhttp() {
/*  53 */     return Intrinsics.areEqual(this.finder.getAddress$okhttp().url().host(), this.connection.route().address().url().host()) ^ true;
/*     */   }
/*     */   
/*     */   public final void writeRequestHeaders(@NotNull Request request) throws IOException {
/*  57 */     Intrinsics.checkNotNullParameter(request, "request"); try {
/*  58 */       this.eventListener.requestHeadersStart(this.call);
/*  59 */       this.codec.writeRequestHeaders(request);
/*  60 */       this.eventListener.requestHeadersEnd(this.call, request);
/*  61 */     } catch (IOException e) {
/*  62 */       this.eventListener.requestFailed(this.call, e);
/*  63 */       trackFailure(e);
/*  64 */       throw (Throwable)e;
/*     */     } 
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public final Sink createRequestBody(@NotNull Request request, boolean duplex) throws IOException {
/*  70 */     Intrinsics.checkNotNullParameter(request, "request"); this.isDuplex = duplex;
/*  71 */     Intrinsics.checkNotNull(request.body()); long contentLength = request.body().contentLength();
/*  72 */     this.eventListener.requestBodyStart(this.call);
/*  73 */     Sink rawRequestBody = this.codec.createRequestBody(request, contentLength);
/*  74 */     return (Sink)new RequestBodySink(rawRequestBody, contentLength);
/*     */   }
/*     */ 
/*     */   
/*     */   public final void flushRequest() throws IOException {
/*     */     try {
/*  80 */       this.codec.flushRequest();
/*  81 */     } catch (IOException e) {
/*  82 */       this.eventListener.requestFailed(this.call, e);
/*  83 */       trackFailure(e);
/*  84 */       throw (Throwable)e;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final void finishRequest() throws IOException {
/*     */     try {
/*  91 */       this.codec.finishRequest();
/*  92 */     } catch (IOException e) {
/*  93 */       this.eventListener.requestFailed(this.call, e);
/*  94 */       trackFailure(e);
/*  95 */       throw (Throwable)e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void responseHeadersStart() {
/* 100 */     this.eventListener.responseHeadersStart(this.call);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public final Response.Builder readResponseHeaders(boolean expectContinue) throws IOException {
/*     */     try {
/* 106 */       Response.Builder result = this.codec.readResponseHeaders(expectContinue);
/* 107 */       if (result != null) { result.initExchange$okhttp(this); } else {  }
/* 108 */        return result;
/* 109 */     } catch (IOException e) {
/* 110 */       this.eventListener.responseFailed(this.call, e);
/* 111 */       trackFailure(e);
/* 112 */       throw (Throwable)e;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void responseHeadersEnd(@NotNull Response response) {
/* 117 */     Intrinsics.checkNotNullParameter(response, "response"); this.eventListener.responseHeadersEnd(this.call, response);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public final ResponseBody openResponseBody(@NotNull Response response) throws IOException {
/* 122 */     Intrinsics.checkNotNullParameter(response, "response"); try {
/* 123 */       String contentType = Response.header$default(response, "Content-Type", null, 2, null);
/* 124 */       long contentLength = this.codec.reportedContentLength(response);
/* 125 */       Source rawSource = this.codec.openResponseBodySource(response);
/* 126 */       ResponseBodySource source = new ResponseBodySource(rawSource, contentLength);
/* 127 */       return (ResponseBody)new RealResponseBody(contentType, contentLength, Okio.buffer((Source)source));
/* 128 */     } catch (IOException e) {
/* 129 */       this.eventListener.responseFailed(this.call, e);
/* 130 */       trackFailure(e);
/* 131 */       throw (Throwable)e;
/*     */     } 
/*     */   }
/*     */   @NotNull
/*     */   public final Headers trailers() throws IOException {
/* 136 */     return this.codec.trailers();
/*     */   }
/*     */   @NotNull
/*     */   public final RealWebSocket.Streams newWebSocketStreams() throws SocketException {
/* 140 */     this.call.timeoutEarlyExit();
/* 141 */     return this.codec.getConnection().newWebSocketStreams$okhttp(this);
/*     */   }
/*     */   
/*     */   public final void webSocketUpgradeFailed() {
/* 145 */     bodyComplete(-1L, true, true, null);
/*     */   }
/*     */   
/*     */   public final void noNewExchangesOnConnection() {
/* 149 */     this.codec.getConnection().noNewExchanges$okhttp();
/*     */   }
/*     */   
/*     */   public final void cancel() {
/* 153 */     this.codec.cancel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void detachWithViolence() {
/* 161 */     this.codec.cancel();
/* 162 */     this.call.messageDone$okhttp(this, true, true, null);
/*     */   }
/*     */   
/*     */   private final void trackFailure(IOException e) {
/* 166 */     this.finder.trackFailure(e);
/* 167 */     this.codec.getConnection().trackFailure$okhttp(this.call, e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final <E extends IOException> E bodyComplete(long bytesRead, boolean responseDone, boolean requestDone, IOException e) {
/* 176 */     if (e != null) {
/* 177 */       trackFailure(e);
/*     */     }
/* 179 */     if (requestDone) {
/* 180 */       if (e != null) {
/* 181 */         this.eventListener.requestFailed(this.call, e);
/*     */       } else {
/* 183 */         this.eventListener.requestBodyEnd(this.call, bytesRead);
/*     */       } 
/*     */     }
/* 186 */     if (responseDone) {
/* 187 */       if (e != null) {
/* 188 */         this.eventListener.responseFailed(this.call, e);
/*     */       } else {
/* 190 */         this.eventListener.responseBodyEnd(this.call, bytesRead);
/*     */       } 
/*     */     }
/* 193 */     return this.call.messageDone$okhttp(this, requestDone, responseDone, (E)e);
/*     */   }
/*     */   
/*     */   public final void noRequestBody() {
/* 197 */     this.call.messageDone$okhttp(this, true, false, null);
/*     */   } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0008\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\020\013\n\002\b\002\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\002\b\004\030\0002\0020\001B\025\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005¢\006\002\020\006J\b\020\013\032\0020\fH\026J!\020\r\032\002H\016\"\n\b\000\020\016*\004\030\0010\0172\006\020\020\032\002H\016H\002¢\006\002\020\021J\b\020\022\032\0020\fH\026J\030\020\023\032\0020\f2\006\020\024\032\0020\0252\006\020\026\032\0020\005H\026R\016\020\007\032\0020\005X\016¢\006\002\n\000R\016\020\b\032\0020\tX\016¢\006\002\n\000R\016\020\n\032\0020\tX\016¢\006\002\n\000R\016\020\004\032\0020\005X\004¢\006\002\n\000¨\006\027"}, d2 = {"Lokhttp3/internal/connection/Exchange$RequestBodySink;", "Lokio/ForwardingSink;", "delegate", "Lokio/Sink;", "contentLength", "", "(Lokhttp3/internal/connection/Exchange;Lokio/Sink;J)V", "bytesReceived", "closed", "", "completed", "close", "", "complete", "E", "Ljava/io/IOException;", "e", "(Ljava/io/IOException;)Ljava/io/IOException;", "flush", "write", "source", "Lokio/Buffer;", "byteCount", "okhttp"}) private final class RequestBodySink extends ForwardingSink {
/*     */     private boolean completed; private long bytesReceived; private boolean closed; private final long contentLength; public void flush() throws IOException { try { super.flush(); }
/*     */       catch (IOException e) { throw (Throwable)complete(e); }
/*     */        } public void close() throws IOException { if (this.closed)
/*     */         return;  this.closed = true; if (this.contentLength != -1L && this.bytesReceived != this.contentLength)
/*     */         throw (Throwable)new ProtocolException("unexpected end of stream");  try { super.close(); complete(null); }
/*     */       catch (IOException e) { throw (Throwable)complete(e); }
/* 205 */        } public RequestBodySink(Sink delegate, long contentLength) { super(delegate); this.contentLength = contentLength; }
/*     */      private final <E extends IOException> E complete(IOException e) {
/*     */       if (this.completed)
/*     */         return (E)e; 
/*     */       this.completed = true;
/*     */       return Exchange.this.bodyComplete(this.bytesReceived, false, true, (E)e);
/*     */     } public void write(@NotNull Buffer source, long byteCount) throws IOException {
/* 212 */       Intrinsics.checkNotNullParameter(source, "source"); boolean bool1 = !this.closed ? true : false, bool2 = false, bool3 = false; if (!bool1)
/*     */       
/*     */       { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 329 */         int $i$a$-check-Exchange$RequestBodySink$write$1 = 0; String str = "closed"; throw (Throwable)new IllegalStateException(str.toString()); }  if (this.contentLength != -1L && this.bytesReceived + byteCount > this.contentLength) throw (Throwable)new ProtocolException("expected " + this.contentLength + " bytes but received " + (this.bytesReceived + byteCount));  try { super.write(source, byteCount); this.bytesReceived += byteCount; } catch (IOException e) { throw (Throwable)complete(e); }  } } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0008\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\t\n\002\b\003\n\002\020\013\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\002\b\004\030\0002\0020\001B\025\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005¢\006\002\020\006J\b\020\f\032\0020\rH\026J\037\020\016\032\002H\017\"\n\b\000\020\017*\004\030\0010\0202\006\020\021\032\002H\017¢\006\002\020\022J\030\020\023\032\0020\0052\006\020\024\032\0020\0252\006\020\026\032\0020\005H\026R\016\020\007\032\0020\005X\016¢\006\002\n\000R\016\020\b\032\0020\tX\016¢\006\002\n\000R\016\020\n\032\0020\tX\016¢\006\002\n\000R\016\020\004\032\0020\005X\004¢\006\002\n\000R\016\020\013\032\0020\tX\016¢\006\002\n\000¨\006\027"}, d2 = {"Lokhttp3/internal/connection/Exchange$ResponseBodySource;", "Lokio/ForwardingSource;", "delegate", "Lokio/Source;", "contentLength", "", "(Lokhttp3/internal/connection/Exchange;Lokio/Source;J)V", "bytesReceived", "closed", "", "completed", "invokeStartEvent", "close", "", "complete", "E", "Ljava/io/IOException;", "e", "(Ljava/io/IOException;)Ljava/io/IOException;", "read", "sink", "Lokio/Buffer;", "byteCount", "okhttp"}) public final class ResponseBodySource extends ForwardingSource { public long read(@NotNull Buffer sink, long byteCount) throws IOException { Intrinsics.checkNotNullParameter(sink, "sink"); boolean bool1 = !this.closed ? true : false, bool2 = false, bool3 = false; if (!bool1) { int $i$a$-check-Exchange$ResponseBodySource$read$1 = 0;
/*     */         String str = "closed";
/*     */         throw (Throwable)new IllegalStateException(str.toString()); }
/*     */       
/*     */       try {
/*     */         long read = delegate().read(sink, byteCount);
/*     */         if (this.invokeStartEvent) {
/*     */           this.invokeStartEvent = false;
/*     */           Exchange.this.getEventListener$okhttp().responseBodyStart(Exchange.this.getCall$okhttp());
/*     */         } 
/*     */         if (read == -1L) {
/*     */           complete(null);
/*     */           return -1L;
/*     */         } 
/*     */         long newBytesReceived = this.bytesReceived + read;
/*     */         if (this.contentLength != -1L && newBytesReceived > this.contentLength)
/*     */           throw (Throwable)new ProtocolException("expected " + this.contentLength + " bytes but received " + newBytesReceived); 
/*     */         this.bytesReceived = newBytesReceived;
/*     */         if (newBytesReceived == this.contentLength)
/*     */           complete(null); 
/*     */         return read;
/*     */       } catch (IOException e) {
/*     */         throw (Throwable)complete(e);
/*     */       }  }
/*     */ 
/*     */     
/*     */     private long bytesReceived;
/*     */     private boolean invokeStartEvent;
/*     */     private boolean completed;
/*     */     private boolean closed;
/*     */     private final long contentLength;
/*     */     
/*     */     public ResponseBodySource(Source delegate, long contentLength) {
/*     */       super(delegate);
/*     */       this.contentLength = contentLength;
/*     */       this.invokeStartEvent = true;
/*     */       if (this.contentLength == 0L)
/*     */         complete(null); 
/*     */     }
/*     */     
/*     */     public void close() throws IOException {
/*     */       if (this.closed)
/*     */         return; 
/*     */       this.closed = true;
/*     */       try {
/*     */         super.close();
/*     */         complete(null);
/*     */       } catch (IOException e) {
/*     */         throw (Throwable)complete(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public final <E extends IOException> E complete(IOException e) {
/*     */       if (this.completed)
/*     */         return (E)e; 
/*     */       this.completed = true;
/*     */       if (e == null && this.invokeStartEvent) {
/*     */         this.invokeStartEvent = false;
/*     */         Exchange.this.getEventListener$okhttp().responseBodyStart(Exchange.this.getCall$okhttp());
/*     */       } 
/*     */       return Exchange.this.bodyComplete(this.bytesReceived, true, false, (E)e);
/*     */     } }
/*     */ 
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/connection/Exchange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */