/*      */ package okhttp3.internal.http2;
/*      */ 
/*      */ import java.io.Closeable;
/*      */ import java.io.IOException;
/*      */ import java.io.InterruptedIOException;
/*      */ import java.net.Socket;
/*      */ import java.util.Collection;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.LinkedHashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.TimeUnit;
/*      */ import kotlin.Metadata;
/*      */ import kotlin.Unit;
/*      */ import kotlin.jvm.JvmField;
/*      */ import kotlin.jvm.JvmOverloads;
/*      */ import kotlin.jvm.functions.Function0;
/*      */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*      */ import kotlin.jvm.internal.Intrinsics;
/*      */ import kotlin.jvm.internal.Ref;
/*      */ import okhttp3.Headers;
/*      */ import okhttp3.internal.Util;
/*      */ import okhttp3.internal.concurrent.Task;
/*      */ import okhttp3.internal.concurrent.TaskQueue;
/*      */ import okhttp3.internal.concurrent.TaskRunner;
/*      */ import okio.Buffer;
/*      */ import okio.BufferedSink;
/*      */ import okio.BufferedSource;
/*      */ import okio.ByteString;
/*      */ import okio.Okio;
/*      */ import org.jetbrains.annotations.NotNull;
/*      */ import org.jetbrains.annotations.Nullable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000´\001\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\t\n\002\b\002\n\002\020\013\n\002\b\003\n\002\020\016\n\002\b\003\n\002\020#\n\002\020\b\n\002\b\f\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\007\n\002\030\002\n\000\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\003\n\002\020%\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\004\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\013\n\002\020 \n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\035\n\002\030\002\n\002\b\024\030\000 \0012\0020\001:\b\001\001\001\001B\017\b\000\022\006\020\002\032\0020\003¢\006\002\020\004J\006\020P\032\0020QJ\b\020R\032\0020QH\026J'\020R\032\0020Q2\006\020S\032\0020T2\006\020U\032\0020T2\b\020V\032\004\030\0010WH\000¢\006\002\bXJ\022\020Y\032\0020Q2\b\020Z\032\004\030\0010WH\002J\006\020[\032\0020QJ\020\020\\\032\004\030\0010B2\006\020]\032\0020\022J\016\020^\032\0020\t2\006\020_\032\0020\006J&\020`\032\0020B2\006\020a\032\0020\0222\f\020b\032\b\022\004\022\0020d0c2\006\020e\032\0020\tH\002J\034\020`\032\0020B2\f\020b\032\b\022\004\022\0020d0c2\006\020e\032\0020\tJ\006\020f\032\0020\022J-\020g\032\0020Q2\006\020h\032\0020\0222\006\020i\032\0020j2\006\020k\032\0020\0222\006\020l\032\0020\tH\000¢\006\002\bmJ+\020n\032\0020Q2\006\020h\032\0020\0222\f\020b\032\b\022\004\022\0020d0c2\006\020l\032\0020\tH\000¢\006\002\boJ#\020p\032\0020Q2\006\020h\032\0020\0222\f\020b\032\b\022\004\022\0020d0cH\000¢\006\002\bqJ\035\020r\032\0020Q2\006\020h\032\0020\0222\006\020s\032\0020TH\000¢\006\002\btJ$\020u\032\0020B2\006\020a\032\0020\0222\f\020b\032\b\022\004\022\0020d0c2\006\020e\032\0020\tJ\025\020v\032\0020\t2\006\020h\032\0020\022H\000¢\006\002\bwJ\027\020x\032\004\030\0010B2\006\020h\032\0020\022H\000¢\006\002\byJ\r\020z\032\0020QH\000¢\006\002\b{J\016\020|\032\0020Q2\006\020}\032\0020&J\016\020~\032\0020Q2\006\020\032\0020TJ\036\020\001\032\0020Q2\t\b\002\020\001\032\0020\t2\b\b\002\020E\032\0020FH\007J\030\020\001\032\0020Q2\007\020\001\032\0020\006H\000¢\006\003\b\001J,\020\001\032\0020Q2\006\020h\032\0020\0222\007\020\001\032\0020\t2\n\020\001\032\005\030\0010\0012\006\020k\032\0020\006J/\020\001\032\0020Q2\006\020h\032\0020\0222\007\020\001\032\0020\t2\r\020\001\032\b\022\004\022\0020d0cH\000¢\006\003\b\001J\007\020\001\032\0020QJ\"\020\001\032\0020Q2\007\020\001\032\0020\t2\007\020\001\032\0020\0222\007\020\001\032\0020\022J\007\020\001\032\0020QJ\037\020\001\032\0020Q2\006\020h\032\0020\0222\006\020\032\0020TH\000¢\006\003\b\001J\037\020\001\032\0020Q2\006\020h\032\0020\0222\006\020s\032\0020TH\000¢\006\003\b\001J \020\001\032\0020Q2\006\020h\032\0020\0222\007\020\001\032\0020\006H\000¢\006\003\b\001R\016\020\005\032\0020\006X\016¢\006\002\n\000R\016\020\007\032\0020\006X\016¢\006\002\n\000R\024\020\b\032\0020\tX\004¢\006\b\n\000\032\004\b\n\020\013R\024\020\f\032\0020\rX\004¢\006\b\n\000\032\004\b\016\020\017R\024\020\020\032\b\022\004\022\0020\0220\021X\004¢\006\002\n\000R\016\020\023\032\0020\006X\016¢\006\002\n\000R\016\020\024\032\0020\006X\016¢\006\002\n\000R\016\020\025\032\0020\006X\016¢\006\002\n\000R\016\020\026\032\0020\006X\016¢\006\002\n\000R\016\020\027\032\0020\006X\016¢\006\002\n\000R\016\020\030\032\0020\tX\016¢\006\002\n\000R\032\020\031\032\0020\022X\016¢\006\016\n\000\032\004\b\032\020\033\"\004\b\034\020\035R\024\020\036\032\0020\037X\004¢\006\b\n\000\032\004\b \020!R\032\020\"\032\0020\022X\016¢\006\016\n\000\032\004\b#\020\033\"\004\b$\020\035R\021\020%\032\0020&¢\006\b\n\000\032\004\b'\020(R\032\020)\032\0020&X\016¢\006\016\n\000\032\004\b*\020(\"\004\b+\020,R\016\020-\032\0020.X\004¢\006\002\n\000R\016\020/\032\00200X\004¢\006\002\n\000R\036\0202\032\0020\0062\006\0201\032\0020\006@BX\016¢\006\b\n\000\032\004\b3\0204R\036\0205\032\0020\0062\006\0201\032\0020\006@BX\016¢\006\b\n\000\032\004\b6\0204R\025\0207\032\00608R\0020\000¢\006\b\n\000\032\004\b9\020:R\016\020;\032\00200X\004¢\006\002\n\000R\024\020<\032\0020=X\004¢\006\b\n\000\032\004\b>\020?R \020@\032\016\022\004\022\0020\022\022\004\022\0020B0AX\004¢\006\b\n\000\032\004\bC\020DR\016\020E\032\0020FX\004¢\006\002\n\000R\036\020G\032\0020\0062\006\0201\032\0020\006@BX\016¢\006\b\n\000\032\004\bH\0204R\036\020I\032\0020\0062\006\0201\032\0020\006@BX\016¢\006\b\n\000\032\004\bJ\0204R\021\020K\032\0020L¢\006\b\n\000\032\004\bM\020NR\016\020O\032\00200X\004¢\006\002\n\000¨\006\001"}, d2 = {"Lokhttp3/internal/http2/Http2Connection;", "Ljava/io/Closeable;", "builder", "Lokhttp3/internal/http2/Http2Connection$Builder;", "(Lokhttp3/internal/http2/Http2Connection$Builder;)V", "awaitPingsSent", "", "awaitPongsReceived", "client", "", "getClient$okhttp", "()Z", "connectionName", "", "getConnectionName$okhttp", "()Ljava/lang/String;", "currentPushRequests", "", "", "degradedPingsSent", "degradedPongDeadlineNs", "degradedPongsReceived", "intervalPingsSent", "intervalPongsReceived", "isShutdown", "lastGoodStreamId", "getLastGoodStreamId$okhttp", "()I", "setLastGoodStreamId$okhttp", "(I)V", "listener", "Lokhttp3/internal/http2/Http2Connection$Listener;", "getListener$okhttp", "()Lokhttp3/internal/http2/Http2Connection$Listener;", "nextStreamId", "getNextStreamId$okhttp", "setNextStreamId$okhttp", "okHttpSettings", "Lokhttp3/internal/http2/Settings;", "getOkHttpSettings", "()Lokhttp3/internal/http2/Settings;", "peerSettings", "getPeerSettings", "setPeerSettings", "(Lokhttp3/internal/http2/Settings;)V", "pushObserver", "Lokhttp3/internal/http2/PushObserver;", "pushQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "<set-?>", "readBytesAcknowledged", "getReadBytesAcknowledged", "()J", "readBytesTotal", "getReadBytesTotal", "readerRunnable", "Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "getReaderRunnable", "()Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "settingsListenerQueue", "socket", "Ljava/net/Socket;", "getSocket$okhttp", "()Ljava/net/Socket;", "streams", "", "Lokhttp3/internal/http2/Http2Stream;", "getStreams$okhttp", "()Ljava/util/Map;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "writeBytesMaximum", "getWriteBytesMaximum", "writeBytesTotal", "getWriteBytesTotal", "writer", "Lokhttp3/internal/http2/Http2Writer;", "getWriter", "()Lokhttp3/internal/http2/Http2Writer;", "writerQueue", "awaitPong", "", "close", "connectionCode", "Lokhttp3/internal/http2/ErrorCode;", "streamCode", "cause", "Ljava/io/IOException;", "close$okhttp", "failConnection", "e", "flush", "getStream", "id", "isHealthy", "nowNs", "newStream", "associatedStreamId", "requestHeaders", "", "Lokhttp3/internal/http2/Header;", "out", "openStreamCount", "pushDataLater", "streamId", "source", "Lokio/BufferedSource;", "byteCount", "inFinished", "pushDataLater$okhttp", "pushHeadersLater", "pushHeadersLater$okhttp", "pushRequestLater", "pushRequestLater$okhttp", "pushResetLater", "errorCode", "pushResetLater$okhttp", "pushStream", "pushedStream", "pushedStream$okhttp", "removeStream", "removeStream$okhttp", "sendDegradedPingLater", "sendDegradedPingLater$okhttp", "setSettings", "settings", "shutdown", "statusCode", "start", "sendConnectionPreface", "updateConnectionFlowControl", "read", "updateConnectionFlowControl$okhttp", "writeData", "outFinished", "buffer", "Lokio/Buffer;", "writeHeaders", "alternating", "writeHeaders$okhttp", "writePing", "reply", "payload1", "payload2", "writePingAndAwaitPong", "writeSynReset", "writeSynReset$okhttp", "writeSynResetLater", "writeSynResetLater$okhttp", "writeWindowUpdateLater", "unacknowledgedBytesRead", "writeWindowUpdateLater$okhttp", "Builder", "Companion", "Listener", "ReaderRunnable", "okhttp"})
/*      */ public final class Http2Connection
/*      */   implements Closeable
/*      */ {
/*      */   private final boolean client;
/*      */   @NotNull
/*      */   private final Listener listener;
/*      */   @NotNull
/*      */   private final Map<Integer, Http2Stream> streams;
/*      */   @NotNull
/*      */   private final String connectionName;
/*      */   private int lastGoodStreamId;
/*      */   private int nextStreamId;
/*      */   private boolean isShutdown;
/*      */   private final TaskRunner taskRunner;
/*      */   private final TaskQueue writerQueue;
/*      */   private final TaskQueue pushQueue;
/*      */   private final TaskQueue settingsListenerQueue;
/*      */   private final PushObserver pushObserver;
/*      */   private long intervalPingsSent;
/*      */   private long intervalPongsReceived;
/*      */   private long degradedPingsSent;
/*      */   private long degradedPongsReceived;
/*      */   private long awaitPingsSent;
/*      */   
/*      */   public Http2Connection(@NotNull Builder builder) {
/*   69 */     this.client = builder.getClient$okhttp();
/*      */ 
/*      */     
/*   72 */     this.listener = builder.getListener$okhttp();
/*   73 */     boolean bool2 = false; this.streams = new LinkedHashMap<>();
/*   74 */     this.connectionName = builder.getConnectionName$okhttp();
/*      */ 
/*      */ 
/*      */     
/*   78 */     this.nextStreamId = builder.getClient$okhttp() ? 3 : 2;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   83 */     this.taskRunner = builder.getTaskRunner$okhttp();
/*      */ 
/*      */     
/*   86 */     this.writerQueue = this.taskRunner.newQueue();
/*      */ 
/*      */     
/*   89 */     this.pushQueue = this.taskRunner.newQueue();
/*      */ 
/*      */     
/*   92 */     this.settingsListenerQueue = this.taskRunner.newQueue();
/*      */ 
/*      */     
/*   95 */     this.pushObserver = builder.getPushObserver$okhttp();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  109 */     Settings settings1 = new Settings(); boolean bool3 = false, bool4 = false; Settings settings2 = settings1; Http2Connection http2Connection = this; int $i$a$-apply-Http2Connection$okHttpSettings$1 = 0;
/*      */ 
/*      */ 
/*      */     
/*  113 */     if (builder.getClient$okhttp()) {
/*  114 */       settings2.set(7, 16777216);
/*      */     }
/*  116 */     Unit unit = Unit.INSTANCE; http2Connection.okHttpSettings = settings1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  122 */     this.peerSettings = DEFAULT_SETTINGS;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  137 */     this.writeBytesMaximum = this.peerSettings.getInitialWindowSize();
/*      */ 
/*      */     
/*  140 */     this.socket = builder.getSocket$okhttp();
/*  141 */     this.writer = new Http2Writer(builder.getSink$okhttp(), this.client);
/*      */ 
/*      */     
/*  144 */     this.readerRunnable = new ReaderRunnable(new Http2Reader(builder.getSource$okhttp(), this.client));
/*      */ 
/*      */     
/*  147 */     boolean bool1 = false; this.currentPushRequests = new LinkedHashSet<>();
/*      */ 
/*      */     
/*  150 */     if (builder.getPingIntervalMillis$okhttp() != 0) {
/*  151 */       long pingIntervalNanos = TimeUnit.MILLISECONDS.toNanos(builder.getPingIntervalMillis$okhttp());
/*  152 */       TaskQueue taskQueue = this.writerQueue; String name$iv = this.connectionName + " ping"; int $i$f$schedule = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1127 */       taskQueue.schedule(new Http2Connection$$special$$inlined$schedule$1(name$iv, name$iv, this, pingIntervalNanos), 
/*      */           
/* 1129 */           pingIntervalNanos);
/*      */     } 
/*      */   }
/*      */   
/*      */   private long awaitPongsReceived;
/*      */   private long degradedPongDeadlineNs;
/*      */   @NotNull
/*      */   private final Settings okHttpSettings;
/*      */   @NotNull
/*      */   private Settings peerSettings;
/*      */   private long readBytesTotal;
/*      */   private long readBytesAcknowledged;
/*      */   private long writeBytesTotal;
/*      */   private long writeBytesMaximum;
/*      */   @NotNull
/*      */   private final Socket socket;
/*      */   @NotNull
/*      */   private final Http2Writer writer;
/*      */   @NotNull
/*      */   private final ReaderRunnable readerRunnable;
/*      */   private final Set<Integer> currentPushRequests;
/*      */   public static final int OKHTTP_CLIENT_WINDOW_SIZE = 16777216;
/*      */   @NotNull
/*      */   private static final Settings DEFAULT_SETTINGS;
/*      */   public static final int INTERVAL_PING = 1;
/*      */   public static final int DEGRADED_PING = 2;
/*      */   public static final int AWAIT_PING = 3;
/*      */   public static final int DEGRADED_PONG_TIMEOUT_NS = 1000000000;
/*      */   
/*      */   public final boolean getClient$okhttp() {
/*      */     return this.client;
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   public final Listener getListener$okhttp() {
/*      */     return this.listener;
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   public final Map<Integer, Http2Stream> getStreams$okhttp() {
/*      */     return this.streams;
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   public final String getConnectionName$okhttp() {
/*      */     return this.connectionName;
/*      */   }
/*      */   
/*      */   public final int getLastGoodStreamId$okhttp() {
/*      */     return this.lastGoodStreamId;
/*      */   }
/*      */   
/*      */   public final void setLastGoodStreamId$okhttp(int <set-?>) {
/*      */     this.lastGoodStreamId = <set-?>;
/*      */   }
/*      */   
/*      */   public final int getNextStreamId$okhttp() {
/*      */     return this.nextStreamId;
/*      */   }
/*      */   
/*      */   public final void setNextStreamId$okhttp(int <set-?>) {
/*      */     this.nextStreamId = <set-?>;
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   public final Settings getOkHttpSettings() {
/*      */     return this.okHttpSettings;
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   public final Settings getPeerSettings() {
/*      */     return this.peerSettings;
/*      */   }
/*      */   
/*      */   public final void setPeerSettings(@NotNull Settings <set-?>) {
/*      */     Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
/*      */     this.peerSettings = <set-?>;
/*      */   }
/*      */   
/*      */   public final long getReadBytesTotal() {
/*      */     return this.readBytesTotal;
/*      */   }
/*      */   
/*      */   public final long getReadBytesAcknowledged() {
/*      */     return this.readBytesAcknowledged;
/*      */   }
/*      */   
/*      */   public final long getWriteBytesTotal() {
/*      */     return this.writeBytesTotal;
/*      */   }
/*      */   
/*      */   public final long getWriteBytesMaximum() {
/*      */     return this.writeBytesMaximum;
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   public final Socket getSocket$okhttp() {
/*      */     return this.socket;
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   public final Http2Writer getWriter() {
/*      */     return this.writer;
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   public final ReaderRunnable getReaderRunnable() {
/*      */     return this.readerRunnable;
/*      */   }
/*      */   
/*      */   public final synchronized int openStreamCount() {
/*      */     return this.streams.size();
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public final synchronized Http2Stream getStream(int id) {
/*      */     return this.streams.get(Integer.valueOf(id));
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public final synchronized Http2Stream removeStream$okhttp(int streamId) {
/*      */     Http2Stream stream = this.streams.remove(Integer.valueOf(streamId));
/*      */     Object $this$notifyAll$iv = this;
/*      */     int $i$f$notifyAll = 0;
/*      */     if ($this$notifyAll$iv == null)
/*      */       throw new NullPointerException("null cannot be cast to non-null type java.lang.Object"); 
/*      */     $this$notifyAll$iv.notifyAll();
/*      */     return stream;
/*      */   }
/*      */   
/*      */   public final synchronized void updateConnectionFlowControl$okhttp(long read) {
/*      */     this.readBytesTotal += read;
/*      */     long readBytesToAcknowledge = this.readBytesTotal - this.readBytesAcknowledged;
/*      */     if (readBytesToAcknowledge >= (this.okHttpSettings.getInitialWindowSize() / 2)) {
/*      */       writeWindowUpdateLater$okhttp(0, readBytesToAcknowledge);
/*      */       this.readBytesAcknowledged += readBytesToAcknowledge;
/*      */     } 
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   public final Http2Stream pushStream(int associatedStreamId, @NotNull List<Header> requestHeaders, boolean out) throws IOException {
/*      */     Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
/*      */     boolean bool1 = !this.client ? true : false, bool2 = false, bool3 = false;
/*      */     if (!bool1) {
/*      */       int $i$a$-check-Http2Connection$pushStream$1 = 0;
/*      */       String str = "Client cannot push requests.";
/*      */       throw (Throwable)new IllegalStateException(str.toString());
/*      */     } 
/*      */     return newStream(associatedStreamId, requestHeaders, out);
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   public final Http2Stream newStream(@NotNull List<Header> requestHeaders, boolean out) throws IOException {
/*      */     Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
/*      */     return newStream(0, requestHeaders, out);
/*      */   }
/*      */   
/*      */   private final Http2Stream newStream(int associatedStreamId, List<Header> requestHeaders, boolean out) throws IOException {
/*      */     boolean outFinished = !out;
/*      */     boolean inFinished = false;
/*      */     boolean flushHeaders = false;
/*      */     Http2Stream stream = null;
/*      */     int streamId = 0;
/*      */     Http2Writer http2Writer = this.writer;
/*      */     boolean bool = false;
/*      */     synchronized (false) {
/*      */       int $i$a$-synchronized-Http2Connection$newStream$1 = 0;
/*      */       Http2Connection http2Connection = this;
/*      */       boolean bool1 = false;
/*      */       synchronized (false) {
/*      */         int $i$a$-synchronized-Http2Connection$newStream$1$1 = 0;
/*      */         if (this.nextStreamId > 1073741823)
/*      */           shutdown(ErrorCode.REFUSED_STREAM); 
/*      */         if (this.isShutdown)
/*      */           throw (Throwable)new ConnectionShutdownException(); 
/*      */         streamId = this.nextStreamId;
/*      */         this.nextStreamId += 2;
/*      */         stream = new Http2Stream(streamId, this, outFinished, inFinished, null);
/*      */         flushHeaders = (!out || this.writeBytesTotal >= this.writeBytesMaximum || stream.getWriteBytesTotal() >= stream.getWriteBytesMaximum());
/*      */         if (stream.isOpen())
/*      */           this.streams.put(Integer.valueOf(streamId), stream); 
/*      */         Unit unit1 = Unit.INSTANCE;
/*      */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */
/*      */       } 
/*      */       if (associatedStreamId == 0) {
/*      */         this.writer.headers(outFinished, streamId, requestHeaders);
/*      */       } else {
/*      */         boolean bool2 = !this.client ? true : false;
/*      */         bool1 = false;
/*      */         boolean bool3 = false;
/*      */         if (!bool2) {
/*      */           int $i$a$-require-Http2Connection$newStream$1$2 = 0;
/*      */           String str = "client streams shouldn't have associated stream IDs";
/*      */           throw (Throwable)new IllegalArgumentException(str.toString());
/*      */         } 
/*      */         this.writer.pushPromise(associatedStreamId, streamId, requestHeaders);
/*      */       } 
/*      */       Unit unit = Unit.INSTANCE;
/*      */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Writer}, name=null} */
/*      */     } 
/*      */     if (flushHeaders)
/*      */       this.writer.flush(); 
/*      */     return stream;
/*      */   }
/*      */   
/*      */   public final void writeHeaders$okhttp(int streamId, boolean outFinished, @NotNull List<Header> alternating) throws IOException {
/*      */     Intrinsics.checkNotNullParameter(alternating, "alternating");
/*      */     this.writer.headers(outFinished, streamId, alternating);
/*      */   }
/*      */   
/*      */   public final void writeData(int streamId, boolean outFinished, @Nullable Buffer buffer, long byteCount) throws IOException {
/*      */     if (byteCount == 0L) {
/*      */       this.writer.data(outFinished, streamId, buffer, 0);
/*      */       return;
/*      */     } 
/*      */     long l = byteCount;
/*      */     while (l > 0L) {
/*      */       int toWrite = 0;
/*      */       Http2Connection http2Connection = this;
/*      */       boolean bool = false;
/*      */       synchronized (false) {
/*      */         int $i$a$-synchronized-Http2Connection$writeData$1 = 0;
/*      */         try {
/*      */           while (this.writeBytesTotal >= this.writeBytesMaximum) {
/*      */             if (!this.streams.containsKey(Integer.valueOf(streamId)))
/*      */               throw (Throwable)new IOException("stream closed"); 
/*      */             Object $this$wait$iv = this;
/*      */             int $i$f$wait = 0;
/*      */             if ($this$wait$iv == null)
/*      */               throw new NullPointerException("null cannot be cast to non-null type java.lang.Object"); 
/*      */             $this$wait$iv.wait();
/*      */           } 
/*      */         } catch (InterruptedException e) {
/*      */           Thread.currentThread().interrupt();
/*      */           throw (Throwable)new InterruptedIOException();
/*      */         } 
/*      */         long l1 = l, l2 = this.writeBytesMaximum - this.writeBytesTotal;
/*      */         boolean bool1 = false;
/*      */         toWrite = (int)Math.min(l1, l2);
/*      */         int i = toWrite, j = this.writer.maxDataLength();
/*      */         boolean bool2 = false;
/*      */         toWrite = Math.min(i, j);
/*      */         this.writeBytesTotal += toWrite;
/*      */         Unit unit = Unit.INSTANCE;
/*      */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */
/*      */       } 
/*      */       l -= toWrite;
/*      */       this.writer.data((outFinished && l == 0L), streamId, buffer, toWrite);
/*      */     } 
/*      */   }
/*      */   
/*      */   public final void writeSynResetLater$okhttp(int streamId, @NotNull ErrorCode errorCode) {
/*      */     Intrinsics.checkNotNullParameter(errorCode, "errorCode");
/*      */     TaskQueue taskQueue = this.writerQueue;
/*      */     String name$iv = this.connectionName + '[' + streamId + "] writeSynReset";
/*      */     long delayNanos$iv = 0L;
/*      */     boolean cancelable$iv = true;
/*      */     int $i$f$execute = 0;
/*      */     taskQueue.schedule(new Http2Connection$writeSynResetLater$$inlined$execute$1(name$iv, cancelable$iv, name$iv, cancelable$iv, this, streamId, errorCode), delayNanos$iv);
/*      */   }
/*      */   
/*      */   public final void writeSynReset$okhttp(int streamId, @NotNull ErrorCode statusCode) throws IOException {
/*      */     Intrinsics.checkNotNullParameter(statusCode, "statusCode");
/*      */     this.writer.rstStream(streamId, statusCode);
/*      */   }
/*      */   
/*      */   public final void writeWindowUpdateLater$okhttp(int streamId, long unacknowledgedBytesRead) {
/*      */     TaskQueue taskQueue = this.writerQueue;
/*      */     String name$iv = this.connectionName + '[' + streamId + "] windowUpdate";
/*      */     long delayNanos$iv = 0L;
/*      */     boolean cancelable$iv = true;
/*      */     int $i$f$execute = 0;
/*      */     taskQueue.schedule(new Http2Connection$writeWindowUpdateLater$$inlined$execute$1(name$iv, cancelable$iv, name$iv, cancelable$iv, this, streamId, unacknowledgedBytesRead), delayNanos$iv);
/*      */   }
/*      */   
/*      */   public final void writePing(boolean reply, int payload1, int payload2) {
/*      */     try {
/*      */       this.writer.ping(reply, payload1, payload2);
/*      */     } catch (IOException e) {
/*      */       failConnection(e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public final void writePingAndAwaitPong() throws InterruptedException {
/*      */     writePing();
/*      */     awaitPong();
/*      */   }
/*      */   
/*      */   public final void writePing() throws InterruptedException {
/*      */     Http2Connection http2Connection = this;
/*      */     boolean bool = false;
/*      */     synchronized (false) {
/*      */       int $i$a$-synchronized-Http2Connection$writePing$1 = 0;
/*      */       long l2;
/*      */       this.awaitPingsSent = (l2 = this.awaitPingsSent) + 1L;
/*      */       long l1 = l2;
/*      */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */
/*      */     } 
/*      */     writePing(false, 3, 1330343787);
/*      */   }
/*      */   
/*      */   public final synchronized void awaitPong() throws InterruptedException {
/*      */     while (this.awaitPongsReceived < this.awaitPingsSent) {
/*      */       Object $this$wait$iv = this;
/*      */       int $i$f$wait = 0;
/*      */       if ($this$wait$iv == null)
/*      */         throw new NullPointerException("null cannot be cast to non-null type java.lang.Object"); 
/*      */       $this$wait$iv.wait();
/*      */     } 
/*      */   }
/*      */   
/*      */   public final void flush() throws IOException {
/*      */     this.writer.flush();
/*      */   }
/*      */   
/*      */   public final void shutdown(@NotNull ErrorCode statusCode) throws IOException {
/*      */     Intrinsics.checkNotNullParameter(statusCode, "statusCode");
/*      */     Http2Writer http2Writer = this.writer;
/*      */     boolean bool = false;
/*      */     synchronized (false) {
/*      */       int $i$a$-synchronized-Http2Connection$shutdown$1 = 0;
/*      */       int lastGoodStreamId = 0;
/*      */       Http2Connection http2Connection = this;
/*      */       boolean bool1 = false;
/*      */       synchronized (false) {
/*      */         int $i$a$-synchronized-Http2Connection$shutdown$1$1 = 0;
/*      */         if (this.isShutdown) {
/*      */           /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */
/*      */           /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Writer}, name=null} */
/*      */           return;
/*      */         } 
/*      */         this.isShutdown = true;
/*      */         lastGoodStreamId = this.lastGoodStreamId;
/*      */         Unit unit1 = Unit.INSTANCE;
/*      */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */
/*      */       } 
/*      */       this.writer.goAway(lastGoodStreamId, statusCode, Util.EMPTY_BYTE_ARRAY);
/*      */       Unit unit = Unit.INSTANCE;
/*      */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Writer}, name=null} */
/*      */     } 
/*      */   }
/*      */   
/*      */   public void close() {
/*      */     close$okhttp(ErrorCode.NO_ERROR, ErrorCode.CANCEL, null);
/*      */   }
/*      */   
/*      */   public final void close$okhttp(@NotNull ErrorCode connectionCode, @NotNull ErrorCode streamCode, @Nullable IOException cause) {
/*      */     // Byte code:
/*      */     //   0: aload_1
/*      */     //   1: ldc_w 'connectionCode'
/*      */     //   4: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*      */     //   7: aload_2
/*      */     //   8: ldc_w 'streamCode'
/*      */     //   11: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*      */     //   14: aload_0
/*      */     //   15: astore #4
/*      */     //   17: iconst_0
/*      */     //   18: istore #5
/*      */     //   20: getstatic okhttp3/internal/Util.assertionsEnabled : Z
/*      */     //   23: ifeq -> 88
/*      */     //   26: aload #4
/*      */     //   28: invokestatic holdsLock : (Ljava/lang/Object;)Z
/*      */     //   31: ifeq -> 88
/*      */     //   34: new java/lang/AssertionError
/*      */     //   37: dup
/*      */     //   38: new java/lang/StringBuilder
/*      */     //   41: dup
/*      */     //   42: invokespecial <init> : ()V
/*      */     //   45: ldc_w 'Thread '
/*      */     //   48: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   51: invokestatic currentThread : ()Ljava/lang/Thread;
/*      */     //   54: dup
/*      */     //   55: ldc_w 'Thread.currentThread()'
/*      */     //   58: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
/*      */     //   61: invokevirtual getName : ()Ljava/lang/String;
/*      */     //   64: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   67: ldc_w ' MUST NOT hold lock on '
/*      */     //   70: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   73: aload #4
/*      */     //   75: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*      */     //   78: invokevirtual toString : ()Ljava/lang/String;
/*      */     //   81: invokespecial <init> : (Ljava/lang/Object;)V
/*      */     //   84: checkcast java/lang/Throwable
/*      */     //   87: athrow
/*      */     //   88: nop
/*      */     //   89: iconst_0
/*      */     //   90: istore #4
/*      */     //   92: nop
/*      */     //   93: iconst_0
/*      */     //   94: istore #5
/*      */     //   96: aload_0
/*      */     //   97: aload_1
/*      */     //   98: invokevirtual shutdown : (Lokhttp3/internal/http2/ErrorCode;)V
/*      */     //   101: goto -> 106
/*      */     //   104: astore #6
/*      */     //   106: nop
/*      */     //   107: aconst_null
/*      */     //   108: checkcast [Lokhttp3/internal/http2/Http2Stream;
/*      */     //   111: astore #4
/*      */     //   113: aload_0
/*      */     //   114: astore #5
/*      */     //   116: iconst_0
/*      */     //   117: istore #6
/*      */     //   119: iconst_0
/*      */     //   120: istore #7
/*      */     //   122: aload #5
/*      */     //   124: monitorenter
/*      */     //   125: nop
/*      */     //   126: iconst_0
/*      */     //   127: istore #8
/*      */     //   129: aload_0
/*      */     //   130: getfield streams : Ljava/util/Map;
/*      */     //   133: astore #9
/*      */     //   135: iconst_0
/*      */     //   136: istore #10
/*      */     //   138: aload #9
/*      */     //   140: invokeinterface isEmpty : ()Z
/*      */     //   145: ifne -> 152
/*      */     //   148: iconst_1
/*      */     //   149: goto -> 153
/*      */     //   152: iconst_0
/*      */     //   153: ifeq -> 214
/*      */     //   156: aload_0
/*      */     //   157: getfield streams : Ljava/util/Map;
/*      */     //   160: invokeinterface values : ()Ljava/util/Collection;
/*      */     //   165: astore #9
/*      */     //   167: iconst_0
/*      */     //   168: istore #10
/*      */     //   170: aload #9
/*      */     //   172: astore #11
/*      */     //   174: aload #11
/*      */     //   176: iconst_0
/*      */     //   177: anewarray okhttp3/internal/http2/Http2Stream
/*      */     //   180: invokeinterface toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
/*      */     //   185: dup
/*      */     //   186: ifnonnull -> 200
/*      */     //   189: new java/lang/NullPointerException
/*      */     //   192: dup
/*      */     //   193: ldc_w 'null cannot be cast to non-null type kotlin.Array<T>'
/*      */     //   196: invokespecial <init> : (Ljava/lang/String;)V
/*      */     //   199: athrow
/*      */     //   200: checkcast [Lokhttp3/internal/http2/Http2Stream;
/*      */     //   203: astore #4
/*      */     //   205: aload_0
/*      */     //   206: getfield streams : Ljava/util/Map;
/*      */     //   209: invokeinterface clear : ()V
/*      */     //   214: getstatic kotlin/Unit.INSTANCE : Lkotlin/Unit;
/*      */     //   217: astore #7
/*      */     //   219: aload #5
/*      */     //   221: monitorexit
/*      */     //   222: goto -> 233
/*      */     //   225: astore #7
/*      */     //   227: aload #5
/*      */     //   229: monitorexit
/*      */     //   230: aload #7
/*      */     //   232: athrow
/*      */     //   233: aload #4
/*      */     //   235: dup
/*      */     //   236: ifnull -> 307
/*      */     //   239: astore #5
/*      */     //   241: iconst_0
/*      */     //   242: istore #6
/*      */     //   244: aload #5
/*      */     //   246: astore #7
/*      */     //   248: aload #7
/*      */     //   250: arraylength
/*      */     //   251: istore #8
/*      */     //   253: iconst_0
/*      */     //   254: istore #9
/*      */     //   256: iload #9
/*      */     //   258: iload #8
/*      */     //   260: if_icmpge -> 304
/*      */     //   263: aload #7
/*      */     //   265: iload #9
/*      */     //   267: aaload
/*      */     //   268: astore #10
/*      */     //   270: aload #10
/*      */     //   272: astore #11
/*      */     //   274: iconst_0
/*      */     //   275: istore #12
/*      */     //   277: iconst_0
/*      */     //   278: istore #13
/*      */     //   280: nop
/*      */     //   281: iconst_0
/*      */     //   282: istore #14
/*      */     //   284: aload #11
/*      */     //   286: aload_2
/*      */     //   287: aload_3
/*      */     //   288: invokevirtual close : (Lokhttp3/internal/http2/ErrorCode;Ljava/io/IOException;)V
/*      */     //   291: goto -> 296
/*      */     //   294: astore #15
/*      */     //   296: nop
/*      */     //   297: nop
/*      */     //   298: iinc #9, 1
/*      */     //   301: goto -> 256
/*      */     //   304: goto -> 308
/*      */     //   307: pop
/*      */     //   308: iconst_0
/*      */     //   309: istore #5
/*      */     //   311: nop
/*      */     //   312: iconst_0
/*      */     //   313: istore #6
/*      */     //   315: aload_0
/*      */     //   316: getfield writer : Lokhttp3/internal/http2/Http2Writer;
/*      */     //   319: invokevirtual close : ()V
/*      */     //   322: goto -> 327
/*      */     //   325: astore #7
/*      */     //   327: nop
/*      */     //   328: iconst_0
/*      */     //   329: istore #5
/*      */     //   331: nop
/*      */     //   332: iconst_0
/*      */     //   333: istore #6
/*      */     //   335: aload_0
/*      */     //   336: getfield socket : Ljava/net/Socket;
/*      */     //   339: invokevirtual close : ()V
/*      */     //   342: goto -> 347
/*      */     //   345: astore #7
/*      */     //   347: nop
/*      */     //   348: aload_0
/*      */     //   349: getfield writerQueue : Lokhttp3/internal/concurrent/TaskQueue;
/*      */     //   352: invokevirtual shutdown : ()V
/*      */     //   355: aload_0
/*      */     //   356: getfield pushQueue : Lokhttp3/internal/concurrent/TaskQueue;
/*      */     //   359: invokevirtual shutdown : ()V
/*      */     //   362: aload_0
/*      */     //   363: getfield settingsListenerQueue : Lokhttp3/internal/concurrent/TaskQueue;
/*      */     //   366: invokevirtual shutdown : ()V
/*      */     //   369: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #446	-> 14
/*      */     //   #1033	-> 20
/*      */     //   #1034	-> 34
/*      */     //   #1036	-> 88
/*      */     //   #448	-> 89
/*      */     //   #1037	-> 92
/*      */     //   #1038	-> 93
/*      */     //   #449	-> 96
/*      */     //   #450	-> 101
/*      */     //   #1039	-> 104
/*      */     //   #1040	-> 106
/*      */     //   #1041	-> 106
/*      */     //   #452	-> 107
/*      */     //   #453	-> 113
/*      */     //   #454	-> 129
/*      */     //   #454	-> 153
/*      */     //   #455	-> 156
/*      */     //   #1042	-> 170
/*      */     //   #1043	-> 174
/*      */     //   #456	-> 205
/*      */     //   #458	-> 214
/*      */     //   #453	-> 217
/*      */     //   #460	-> 233
/*      */     //   #1044	-> 244
/*      */     //   #1044	-> 256
/*      */     //   #461	-> 277
/*      */     //   #1045	-> 280
/*      */     //   #1046	-> 281
/*      */     //   #462	-> 284
/*      */     //   #463	-> 291
/*      */     //   #1047	-> 294
/*      */     //   #1048	-> 296
/*      */     //   #1049	-> 296
/*      */     //   #464	-> 297
/*      */     //   #1050	-> 304
/*      */     //   #467	-> 308
/*      */     //   #1051	-> 311
/*      */     //   #1052	-> 312
/*      */     //   #468	-> 315
/*      */     //   #469	-> 322
/*      */     //   #1053	-> 325
/*      */     //   #1054	-> 327
/*      */     //   #1055	-> 327
/*      */     //   #472	-> 328
/*      */     //   #1056	-> 331
/*      */     //   #1057	-> 332
/*      */     //   #473	-> 335
/*      */     //   #474	-> 342
/*      */     //   #1058	-> 345
/*      */     //   #1059	-> 347
/*      */     //   #1060	-> 347
/*      */     //   #477	-> 348
/*      */     //   #478	-> 355
/*      */     //   #479	-> 362
/*      */     //   #480	-> 369
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   17	72	4	$this$assertThreadDoesntHoldLock$iv	Ljava/lang/Object;
/*      */     //   20	69	5	$i$f$assertThreadDoesntHoldLock	I
/*      */     //   96	5	5	$i$a$-ignoreIoExceptions-Http2Connection$close$1	I
/*      */     //   92	15	4	$i$f$ignoreIoExceptions	I
/*      */     //   174	26	11	thisCollection$iv	Ljava/util/Collection;
/*      */     //   167	33	9	$this$toTypedArray$iv	Ljava/util/Collection;
/*      */     //   170	30	10	$i$f$toTypedArray	I
/*      */     //   129	85	8	$i$a$-synchronized-Http2Connection$close$2	I
/*      */     //   284	7	14	$i$a$-ignoreIoExceptions-Http2Connection$close$3$1	I
/*      */     //   280	17	13	$i$f$ignoreIoExceptions	I
/*      */     //   274	24	11	stream	Lokhttp3/internal/http2/Http2Stream;
/*      */     //   277	21	12	$i$a$-forEach-Http2Connection$close$3	I
/*      */     //   270	31	10	element$iv	Ljava/lang/Object;
/*      */     //   241	63	5	$this$forEach$iv	[Ljava/lang/Object;
/*      */     //   244	60	6	$i$f$forEach	I
/*      */     //   315	7	6	$i$a$-ignoreIoExceptions-Http2Connection$close$4	I
/*      */     //   311	17	5	$i$f$ignoreIoExceptions	I
/*      */     //   335	7	6	$i$a$-ignoreIoExceptions-Http2Connection$close$5	I
/*      */     //   331	17	5	$i$f$ignoreIoExceptions	I
/*      */     //   113	257	4	streamsToClose	Ljava/lang/Object;
/*      */     //   0	370	0	this	Lokhttp3/internal/http2/Http2Connection;
/*      */     //   0	370	1	connectionCode	Lokhttp3/internal/http2/ErrorCode;
/*      */     //   0	370	2	streamCode	Lokhttp3/internal/http2/ErrorCode;
/*      */     //   0	370	3	cause	Ljava/io/IOException;
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   92	101	104	java/io/IOException
/*      */     //   125	219	225	finally
/*      */     //   225	227	225	finally
/*      */     //   280	291	294	java/io/IOException
/*      */     //   311	322	325	java/io/IOException
/*      */     //   331	342	345	java/io/IOException
/*      */   }
/*      */   
/*      */   private final void failConnection(IOException e) {
/*      */     close$okhttp(ErrorCode.PROTOCOL_ERROR, ErrorCode.PROTOCOL_ERROR, e);
/*      */   }
/*      */   
/*      */   @JvmOverloads
/*      */   public final void start(boolean sendConnectionPreface, @NotNull TaskRunner taskRunner) throws IOException {
/*      */     Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
/*      */     if (sendConnectionPreface) {
/*      */       this.writer.connectionPreface();
/*      */       this.writer.settings(this.okHttpSettings);
/*      */       int windowSize = this.okHttpSettings.getInitialWindowSize();
/*      */       if (windowSize != 65535)
/*      */         this.writer.windowUpdate(0, (windowSize - 65535)); 
/*      */     } 
/*      */     TaskQueue taskQueue = taskRunner.newQueue();
/*      */     String str = this.connectionName;
/*      */     Function0 block$iv = this.readerRunnable;
/*      */     long delayNanos$iv = 0L;
/*      */     boolean cancelable$iv = true;
/*      */     int $i$f$execute = 0;
/*      */     taskQueue.schedule((Task)new Object(block$iv, str, cancelable$iv, str, cancelable$iv), delayNanos$iv);
/*      */   }
/*      */   
/*      */   public final void setSettings(@NotNull Settings settings) throws IOException {
/*      */     Intrinsics.checkNotNullParameter(settings, "settings");
/*      */     Http2Writer http2Writer = this.writer;
/*      */     boolean bool = false;
/*      */     synchronized (false) {
/*      */       int $i$a$-synchronized-Http2Connection$setSettings$1 = 0;
/*      */       Http2Connection http2Connection = this;
/*      */       boolean bool1 = false;
/*      */       synchronized (false) {
/*      */         int $i$a$-synchronized-Http2Connection$setSettings$1$1 = 0;
/*      */         if (this.isShutdown)
/*      */           throw (Throwable)new ConnectionShutdownException(); 
/*      */         this.okHttpSettings.merge(settings);
/*      */         Unit unit1 = Unit.INSTANCE;
/*      */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */
/*      */       } 
/*      */       this.writer.settings(settings);
/*      */       Unit unit = Unit.INSTANCE;
/*      */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Writer}, name=null} */
/*      */     } 
/*      */   }
/*      */   
/*      */   public final synchronized boolean isHealthy(long nowNs) {
/*      */     if (this.isShutdown)
/*      */       return false; 
/*      */     if (this.degradedPongsReceived < this.degradedPingsSent && nowNs >= this.degradedPongDeadlineNs)
/*      */       return false; 
/*      */     return true;
/*      */   }
/*      */   
/*      */   public final void sendDegradedPingLater$okhttp() {
/*      */     Http2Connection http2Connection = this;
/*      */     boolean bool = false;
/*      */     synchronized (false) {
/*      */       int $i$a$-synchronized-Http2Connection$sendDegradedPingLater$1 = 0;
/*      */       if (this.degradedPongsReceived < this.degradedPingsSent) {
/*      */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */
/*      */         return;
/*      */       } 
/*      */       long l;
/*      */       this.degradedPingsSent = (l = this.degradedPingsSent) + 1L;
/*      */       this.degradedPongDeadlineNs = System.nanoTime() + 1000000000L;
/*      */       Unit unit = Unit.INSTANCE;
/*      */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */
/*      */     } 
/*      */     TaskQueue taskQueue = this.writerQueue;
/*      */     String name$iv = this.connectionName + " ping";
/*      */     long delayNanos$iv = 0L;
/*      */     boolean cancelable$iv = true;
/*      */     int $i$f$execute = 0;
/*      */     taskQueue.schedule(new Http2Connection$sendDegradedPingLater$$inlined$execute$1(name$iv, cancelable$iv, name$iv, cancelable$iv, this), delayNanos$iv);
/*      */   }
/*      */   
/*      */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000X\n\002\030\002\n\002\020\000\n\000\n\002\020\013\n\000\n\002\030\002\n\002\b\006\n\002\020\016\n\002\b\005\n\002\030\002\n\002\b\005\n\002\020\b\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\007\n\002\030\002\n\002\b\002\030\0002\0020\001B\025\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005¢\006\002\020\006J\006\0207\032\00208J\016\020\021\032\0020\0002\006\020\021\032\0020\022J\016\020\027\032\0020\0002\006\020\027\032\0020\030J\016\020\035\032\0020\0002\006\020\035\032\0020\036J.\020)\032\0020\0002\006\020)\032\0020*2\b\b\002\0209\032\0020\f2\b\b\002\020/\032\002002\b\b\002\020#\032\0020$H\007R\032\020\002\032\0020\003X\016¢\006\016\n\000\032\004\b\007\020\b\"\004\b\t\020\nR\032\020\013\032\0020\fX.¢\006\016\n\000\032\004\b\r\020\016\"\004\b\017\020\020R\032\020\021\032\0020\022X\016¢\006\016\n\000\032\004\b\023\020\024\"\004\b\025\020\026R\032\020\027\032\0020\030X\016¢\006\016\n\000\032\004\b\031\020\032\"\004\b\033\020\034R\032\020\035\032\0020\036X\016¢\006\016\n\000\032\004\b\037\020 \"\004\b!\020\"R\032\020#\032\0020$X.¢\006\016\n\000\032\004\b%\020&\"\004\b'\020(R\032\020)\032\0020*X.¢\006\016\n\000\032\004\b+\020,\"\004\b-\020.R\032\020/\032\00200X.¢\006\016\n\000\032\004\b1\0202\"\004\b3\0204R\024\020\004\032\0020\005X\004¢\006\b\n\000\032\004\b5\0206¨\006:"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$Builder;", "", "client", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "(ZLokhttp3/internal/concurrent/TaskRunner;)V", "getClient$okhttp", "()Z", "setClient$okhttp", "(Z)V", "connectionName", "", "getConnectionName$okhttp", "()Ljava/lang/String;", "setConnectionName$okhttp", "(Ljava/lang/String;)V", "listener", "Lokhttp3/internal/http2/Http2Connection$Listener;", "getListener$okhttp", "()Lokhttp3/internal/http2/Http2Connection$Listener;", "setListener$okhttp", "(Lokhttp3/internal/http2/Http2Connection$Listener;)V", "pingIntervalMillis", "", "getPingIntervalMillis$okhttp", "()I", "setPingIntervalMillis$okhttp", "(I)V", "pushObserver", "Lokhttp3/internal/http2/PushObserver;", "getPushObserver$okhttp", "()Lokhttp3/internal/http2/PushObserver;", "setPushObserver$okhttp", "(Lokhttp3/internal/http2/PushObserver;)V", "sink", "Lokio/BufferedSink;", "getSink$okhttp", "()Lokio/BufferedSink;", "setSink$okhttp", "(Lokio/BufferedSink;)V", "socket", "Ljava/net/Socket;", "getSocket$okhttp", "()Ljava/net/Socket;", "setSocket$okhttp", "(Ljava/net/Socket;)V", "source", "Lokio/BufferedSource;", "getSource$okhttp", "()Lokio/BufferedSource;", "setSource$okhttp", "(Lokio/BufferedSource;)V", "getTaskRunner$okhttp", "()Lokhttp3/internal/concurrent/TaskRunner;", "build", "Lokhttp3/internal/http2/Http2Connection;", "peerName", "okhttp"})
/*      */   public static final class Builder {
/*      */     @NotNull
/*      */     public Socket socket;
/*      */     @NotNull
/*      */     public String connectionName;
/*      */     @NotNull
/*      */     public BufferedSource source;
/*      */     @NotNull
/*      */     public BufferedSink sink;
/*      */     @NotNull
/*      */     private Http2Connection.Listener listener;
/*      */     @NotNull
/*      */     private PushObserver pushObserver;
/*      */     private int pingIntervalMillis;
/*      */     private boolean client;
/*      */     @NotNull
/*      */     private final TaskRunner taskRunner;
/*      */     
/*      */     public Builder(boolean client, @NotNull TaskRunner taskRunner) {
/*      */       this.client = client;
/*      */       this.taskRunner = taskRunner;
/*      */       this.listener = Http2Connection.Listener.REFUSE_INCOMING_STREAMS;
/*      */       this.pushObserver = PushObserver.CANCEL;
/*      */     }
/*      */     
/*      */     public final boolean getClient$okhttp() {
/*      */       return this.client;
/*      */     }
/*      */     
/*      */     public final void setClient$okhttp(boolean <set-?>) {
/*      */       this.client = <set-?>;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final TaskRunner getTaskRunner$okhttp() {
/*      */       return this.taskRunner;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Socket getSocket$okhttp() {
/*      */       if (this.socket == null)
/*      */         Intrinsics.throwUninitializedPropertyAccessException("socket"); 
/*      */       return this.socket;
/*      */     }
/*      */     
/*      */     public final void setSocket$okhttp(@NotNull Socket <set-?>) {
/*      */       Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
/*      */       this.socket = <set-?>;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final String getConnectionName$okhttp() {
/*      */       if (this.connectionName == null)
/*      */         Intrinsics.throwUninitializedPropertyAccessException("connectionName"); 
/*      */       return this.connectionName;
/*      */     }
/*      */     
/*      */     public final void setConnectionName$okhttp(@NotNull String <set-?>) {
/*      */       Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
/*      */       this.connectionName = <set-?>;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final BufferedSource getSource$okhttp() {
/*      */       if (this.source == null)
/*      */         Intrinsics.throwUninitializedPropertyAccessException("source"); 
/*      */       return this.source;
/*      */     }
/*      */     
/*      */     public final void setSource$okhttp(@NotNull BufferedSource <set-?>) {
/*      */       Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
/*      */       this.source = <set-?>;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final BufferedSink getSink$okhttp() {
/*      */       if (this.sink == null)
/*      */         Intrinsics.throwUninitializedPropertyAccessException("sink"); 
/*      */       return this.sink;
/*      */     }
/*      */     
/*      */     public final void setSink$okhttp(@NotNull BufferedSink <set-?>) {
/*      */       Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
/*      */       this.sink = <set-?>;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Http2Connection.Listener getListener$okhttp() {
/*      */       return this.listener;
/*      */     }
/*      */     
/*      */     public final void setListener$okhttp(@NotNull Http2Connection.Listener <set-?>) {
/*      */       Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
/*      */       this.listener = <set-?>;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final PushObserver getPushObserver$okhttp() {
/*      */       return this.pushObserver;
/*      */     }
/*      */     
/*      */     public final void setPushObserver$okhttp(@NotNull PushObserver <set-?>) {
/*      */       Intrinsics.checkNotNullParameter(<set-?>, "<set-?>");
/*      */       this.pushObserver = <set-?>;
/*      */     }
/*      */     
/*      */     public final int getPingIntervalMillis$okhttp() {
/*      */       return this.pingIntervalMillis;
/*      */     }
/*      */     
/*      */     public final void setPingIntervalMillis$okhttp(int <set-?>) {
/*      */       this.pingIntervalMillis = <set-?>;
/*      */     }
/*      */     
/*      */     @JvmOverloads
/*      */     @NotNull
/*      */     public final Builder socket(@NotNull Socket socket, @NotNull String peerName, @NotNull BufferedSource source, @NotNull BufferedSink sink) throws IOException {
/*      */       Intrinsics.checkNotNullParameter(socket, "socket");
/*      */       Intrinsics.checkNotNullParameter(peerName, "peerName");
/*      */       Intrinsics.checkNotNullParameter(source, "source");
/*      */       Intrinsics.checkNotNullParameter(sink, "sink");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-Http2Connection$Builder$socket$1 = 0;
/*      */       $this$apply.socket = socket;
/*      */       $this$apply.connectionName = $this$apply.client ? (Util.okHttpName + ' ' + peerName) : ("MockWebServer " + peerName);
/*      */       $this$apply.source = source;
/*      */       $this$apply.sink = sink;
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder listener(@NotNull Http2Connection.Listener listener) {
/*      */       Intrinsics.checkNotNullParameter(listener, "listener");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-Http2Connection$Builder$listener$1 = 0;
/*      */       $this$apply.listener = listener;
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder pushObserver(@NotNull PushObserver pushObserver) {
/*      */       Intrinsics.checkNotNullParameter(pushObserver, "pushObserver");
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-Http2Connection$Builder$pushObserver$1 = 0;
/*      */       $this$apply.pushObserver = pushObserver;
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Builder pingIntervalMillis(int pingIntervalMillis) {
/*      */       Builder builder1 = this;
/*      */       boolean bool1 = false, bool2 = false;
/*      */       Builder $this$apply = builder1;
/*      */       int $i$a$-apply-Http2Connection$Builder$pingIntervalMillis$1 = 0;
/*      */       $this$apply.pingIntervalMillis = pingIntervalMillis;
/*      */       return builder1;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Http2Connection build() {
/*      */       return new Http2Connection(this);
/*      */     }
/*      */     
/*      */     @JvmOverloads
/*      */     @NotNull
/*      */     public final Builder socket(@NotNull Socket socket, @NotNull String peerName, @NotNull BufferedSource source) throws IOException {
/*      */       return socket$default(this, socket, peerName, source, null, 8, null);
/*      */     }
/*      */     
/*      */     @JvmOverloads
/*      */     @NotNull
/*      */     public final Builder socket(@NotNull Socket socket, @NotNull String peerName) throws IOException {
/*      */       return socket$default(this, socket, peerName, null, null, 12, null);
/*      */     }
/*      */     
/*      */     @JvmOverloads
/*      */     @NotNull
/*      */     public final Builder socket(@NotNull Socket socket) throws IOException {
/*      */       return socket$default(this, socket, null, null, null, 14, null);
/*      */     }
/*      */   }
/*      */   
/*      */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000`\n\002\030\002\n\002\030\002\n\002\030\002\n\002\020\002\n\000\n\002\030\002\n\002\b\006\n\002\020\b\n\000\n\002\020\016\n\000\n\002\030\002\n\002\b\003\n\002\020\t\n\002\b\002\n\002\020\013\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\004\n\002\020 \n\002\030\002\n\002\b\020\b\004\030\0002\0020\0012\b\022\004\022\0020\0030\002B\017\b\000\022\006\020\004\032\0020\005¢\006\002\020\006J\b\020\t\032\0020\003H\026J8\020\n\032\0020\0032\006\020\013\032\0020\f2\006\020\r\032\0020\0162\006\020\017\032\0020\0202\006\020\021\032\0020\0162\006\020\022\032\0020\f2\006\020\023\032\0020\024H\026J\026\020\025\032\0020\0032\006\020\026\032\0020\0272\006\020\030\032\0020\031J(\020\032\032\0020\0032\006\020\033\032\0020\0272\006\020\013\032\0020\f2\006\020\034\032\0020\0352\006\020\036\032\0020\fH\026J \020\037\032\0020\0032\006\020 \032\0020\f2\006\020!\032\0020\"2\006\020#\032\0020\020H\026J.\020$\032\0020\0032\006\020\033\032\0020\0272\006\020\013\032\0020\f2\006\020%\032\0020\f2\f\020&\032\b\022\004\022\0020(0'H\026J\t\020)\032\0020\003H\002J \020*\032\0020\0032\006\020+\032\0020\0272\006\020,\032\0020\f2\006\020-\032\0020\fH\026J(\020.\032\0020\0032\006\020\013\032\0020\f2\006\020/\032\0020\f2\006\0200\032\0020\f2\006\0201\032\0020\027H\026J&\0202\032\0020\0032\006\020\013\032\0020\f2\006\0203\032\0020\f2\f\0204\032\b\022\004\022\0020(0'H\026J\030\0205\032\0020\0032\006\020\013\032\0020\f2\006\020!\032\0020\"H\026J\030\020\030\032\0020\0032\006\020\026\032\0020\0272\006\020\030\032\0020\031H\026J\030\0206\032\0020\0032\006\020\013\032\0020\f2\006\0207\032\0020\024H\026R\024\020\004\032\0020\005X\004¢\006\b\n\000\032\004\b\007\020\b¨\0068"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "Lokhttp3/internal/http2/Http2Reader$Handler;", "Lkotlin/Function0;", "", "reader", "Lokhttp3/internal/http2/Http2Reader;", "(Lokhttp3/internal/http2/Http2Connection;Lokhttp3/internal/http2/Http2Reader;)V", "getReader$okhttp", "()Lokhttp3/internal/http2/Http2Reader;", "ackSettings", "alternateService", "streamId", "", "origin", "", "protocol", "Lokio/ByteString;", "host", "port", "maxAge", "", "applyAndAckSettings", "clearPrevious", "", "settings", "Lokhttp3/internal/http2/Settings;", "data", "inFinished", "source", "Lokio/BufferedSource;", "length", "goAway", "lastGoodStreamId", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "debugData", "headers", "associatedStreamId", "headerBlock", "", "Lokhttp3/internal/http2/Header;", "invoke", "ping", "ack", "payload1", "payload2", "priority", "streamDependency", "weight", "exclusive", "pushPromise", "promisedStreamId", "requestHeaders", "rstStream", "windowUpdate", "windowSizeIncrement", "okhttp"})
/*      */   public final class ReaderRunnable implements Http2Reader.Handler, Function0<Unit> {
/*      */     @NotNull
/*      */     private final Http2Reader reader;
/*      */     
/*      */     public ReaderRunnable(Http2Reader reader) {
/*      */       this.reader = reader;
/*      */     }
/*      */     
/*      */     @NotNull
/*      */     public final Http2Reader getReader$okhttp() {
/*      */       return this.reader;
/*      */     }
/*      */     
/*      */     public void invoke() {
/*      */       ErrorCode connectionErrorCode = ErrorCode.INTERNAL_ERROR;
/*      */       ErrorCode streamErrorCode = ErrorCode.INTERNAL_ERROR;
/*      */       IOException errorException = (IOException)null;
/*      */       try {
/*      */         this.reader.readConnectionPreface(this);
/*      */         while (this.reader.nextFrame(false, this));
/*      */         connectionErrorCode = ErrorCode.NO_ERROR;
/*      */         streamErrorCode = ErrorCode.CANCEL;
/*      */       } catch (IOException e) {
/*      */         errorException = e;
/*      */         connectionErrorCode = ErrorCode.PROTOCOL_ERROR;
/*      */         streamErrorCode = ErrorCode.PROTOCOL_ERROR;
/*      */       } finally {
/*      */         Http2Connection.this.close$okhttp(connectionErrorCode, streamErrorCode, errorException);
/*      */         Util.closeQuietly(this.reader);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void data(boolean inFinished, int streamId, @NotNull BufferedSource source, int length) throws IOException {
/*      */       Intrinsics.checkNotNullParameter(source, "source");
/*      */       if (Http2Connection.this.pushedStream$okhttp(streamId)) {
/*      */         Http2Connection.this.pushDataLater$okhttp(streamId, source, length, inFinished);
/*      */         return;
/*      */       } 
/*      */       Http2Stream dataStream = Http2Connection.this.getStream(streamId);
/*      */       if (dataStream == null) {
/*      */         Http2Connection.this.writeSynResetLater$okhttp(streamId, ErrorCode.PROTOCOL_ERROR);
/*      */         Http2Connection.this.updateConnectionFlowControl$okhttp(length);
/*      */         source.skip(length);
/*      */         return;
/*      */       } 
/*      */       dataStream.receiveData(source, length);
/*      */       if (inFinished)
/*      */         dataStream.receiveHeaders(Util.EMPTY_HEADERS, true); 
/*      */     }
/*      */     
/*      */     public void headers(boolean inFinished, int streamId, int associatedStreamId, @NotNull List<Header> headerBlock) {
/*      */       Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
/*      */       if (Http2Connection.this.pushedStream$okhttp(streamId)) {
/*      */         Http2Connection.this.pushHeadersLater$okhttp(streamId, headerBlock, inFinished);
/*      */         return;
/*      */       } 
/*      */       Http2Stream stream = null;
/*      */       Http2Connection http2Connection = Http2Connection.this;
/*      */       boolean bool = false;
/*      */       synchronized (false) {
/*      */         int $i$a$-synchronized-Http2Connection$ReaderRunnable$headers$1 = 0;
/*      */         stream = Http2Connection.this.getStream(streamId);
/*      */         if (stream == null) {
/*      */           if (Http2Connection.this.isShutdown) {
/*      */             /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */
/*      */             return;
/*      */           } 
/*      */           if (streamId <= Http2Connection.this.getLastGoodStreamId$okhttp()) {
/*      */             /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */
/*      */             return;
/*      */           } 
/*      */           if (streamId % 2 == Http2Connection.this.getNextStreamId$okhttp() % 2) {
/*      */             /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */
/*      */             return;
/*      */           } 
/*      */           Headers headers = Util.toHeaders(headerBlock);
/*      */           Http2Stream newStream = new Http2Stream(streamId, Http2Connection.this, false, inFinished, headers);
/*      */           Http2Connection.this.setLastGoodStreamId$okhttp(streamId);
/*      */           Http2Connection.this.getStreams$okhttp().put(Integer.valueOf(streamId), newStream);
/*      */           TaskQueue taskQueue = Http2Connection.this.taskRunner.newQueue();
/*      */           String name$iv = Http2Connection.this.getConnectionName$okhttp() + '[' + streamId + "] onStream";
/*      */           long delayNanos$iv = 0L;
/*      */           boolean cancelable$iv = true;
/*      */           int $i$f$execute = 0;
/*      */           taskQueue.schedule(new Http2Connection$ReaderRunnable$headers$$inlined$synchronized$lambda$1(name$iv, cancelable$iv, name$iv, cancelable$iv, newStream, this, stream, streamId, headerBlock, inFinished), delayNanos$iv);
/*      */           /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */
/*      */           return;
/*      */         } 
/*      */         Unit unit = Unit.INSTANCE;
/*      */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */
/*      */       } 
/*      */       stream.receiveHeaders(Util.toHeaders(headerBlock), inFinished);
/*      */     }
/*      */     
/*      */     public void rstStream(int streamId, @NotNull ErrorCode errorCode) {
/*      */       Intrinsics.checkNotNullParameter(errorCode, "errorCode");
/*      */       if (Http2Connection.this.pushedStream$okhttp(streamId)) {
/*      */         Http2Connection.this.pushResetLater$okhttp(streamId, errorCode);
/*      */         return;
/*      */       } 
/*      */       Http2Stream rstStream = Http2Connection.this.removeStream$okhttp(streamId);
/*      */       if (rstStream != null) {
/*      */         rstStream.receiveRstStream(errorCode);
/*      */       } else {
/*      */       
/*      */       } 
/*      */     }
/*      */     
/*      */     public void settings(boolean clearPrevious, @NotNull Settings settings) {
/*      */       Intrinsics.checkNotNullParameter(settings, "settings");
/*      */       TaskQueue taskQueue = Http2Connection.this.writerQueue;
/*      */       String name$iv = Http2Connection.this.getConnectionName$okhttp() + " applyAndAckSettings";
/*      */       long delayNanos$iv = 0L;
/*      */       boolean cancelable$iv = true;
/*      */       int $i$f$execute = 0;
/*      */       taskQueue.schedule(new Http2Connection$ReaderRunnable$settings$$inlined$execute$1(name$iv, cancelable$iv, name$iv, cancelable$iv, this, clearPrevious, settings), delayNanos$iv);
/*      */     }
/*      */     
/*      */     public final void applyAndAckSettings(boolean clearPrevious, @NotNull Settings settings) {
/*      */       Intrinsics.checkNotNullParameter(settings, "settings");
/*      */       Ref.LongRef delta = new Ref.LongRef();
/*      */       Ref.ObjectRef streamsToNotify = new Ref.ObjectRef();
/*      */       Ref.ObjectRef newPeerSettings = new Ref.ObjectRef();
/*      */       Http2Writer http2Writer = Http2Connection.this.getWriter();
/*      */       byte b = 0;
/*      */       synchronized (false) {
/*      */         int $i$a$-synchronized-Http2Connection$ReaderRunnable$applyAndAckSettings$1 = 0;
/*      */         Http2Connection http2Connection = Http2Connection.this;
/*      */         boolean bool = false;
/*      */         synchronized (false) {
/*      */           int $i$a$-synchronized-Http2Connection$ReaderRunnable$applyAndAckSettings$1$1 = 0;
/*      */           Settings previousPeerSettings = Http2Connection.this.getPeerSettings();
/*      */           Settings settings1 = new Settings();
/*      */           boolean bool1 = false, bool2 = false;
/*      */           Settings settings2 = settings1;
/*      */           Ref.ObjectRef objectRef = newPeerSettings;
/*      */           int $i$a$-apply-Http2Connection$ReaderRunnable$applyAndAckSettings$1$1$1 = 0;
/*      */           settings2.merge(previousPeerSettings);
/*      */           settings2.merge(settings);
/*      */           Unit unit2 = Unit.INSTANCE;
/*      */           objectRef.element = clearPrevious ? settings : settings1;
/*      */           long peerInitialWindowSize = ((Settings)newPeerSettings.element).getInitialWindowSize();
/*      */           delta.element = peerInitialWindowSize - previousPeerSettings.getInitialWindowSize();
/*      */           Collection<Http2Stream> $this$toTypedArray$iv = Http2Connection.this.getStreams$okhttp().values();
/*      */           int $i$f$toTypedArray = 0;
/*      */           Collection<Http2Stream> thisCollection$iv = $this$toTypedArray$iv;
/*      */           if (thisCollection$iv.toArray(new Http2Stream[0]) == null)
/*      */             throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>"); 
/*      */           streamsToNotify.element = (delta.element == 0L || Http2Connection.this.getStreams$okhttp().isEmpty()) ? null : thisCollection$iv.toArray(new Http2Stream[0]);
/*      */           Http2Connection.this.setPeerSettings((Settings)newPeerSettings.element);
/*      */           TaskQueue taskQueue = Http2Connection.this.settingsListenerQueue;
/*      */           String name$iv = Http2Connection.this.getConnectionName$okhttp() + " onSettings";
/*      */           long delayNanos$iv = 0L;
/*      */           boolean cancelable$iv = true;
/*      */           int $i$f$execute = 0;
/*      */           taskQueue.schedule(new Http2Connection$ReaderRunnable$applyAndAckSettings$$inlined$synchronized$lambda$1(name$iv, cancelable$iv, name$iv, cancelable$iv, this, newPeerSettings, clearPrevious, settings, delta, streamsToNotify), delayNanos$iv);
/*      */           Unit unit1 = Unit.INSTANCE;
/*      */           /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */
/*      */         } 
/*      */         try {
/*      */           Http2Connection.this.getWriter().applyAndAckSettings((Settings)newPeerSettings.element);
/*      */         } catch (IOException e) {
/*      */           Http2Connection.this.failConnection(e);
/*      */         } 
/*      */         Unit unit = Unit.INSTANCE;
/*      */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Writer}, name=null} */
/*      */       } 
/*      */       if ((Http2Stream[])streamsToNotify.element != null) {
/*      */         Intrinsics.checkNotNull(streamsToNotify.element);
/*      */         for (Http2Stream stream : (Http2Stream[])streamsToNotify.element) {
/*      */           boolean bool = false;
/*      */           synchronized (false) {
/*      */             int $i$a$-synchronized-Http2Connection$ReaderRunnable$applyAndAckSettings$2 = 0;
/*      */             stream.addBytesToWriteWindow(delta.element);
/*      */             Unit unit = Unit.INSTANCE;
/*      */             /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Stream}, name=stream} */
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public void ackSettings() {}
/*      */     
/*      */     public void ping(boolean ack, int payload1, int payload2) {
/*      */       if (ack) {
/*      */         Http2Connection http2Connection = Http2Connection.this;
/*      */         boolean bool = false;
/*      */         synchronized (false) {
/*      */           long l;
/*      */           Object $this$notifyAll$iv;
/*      */           int $i$f$notifyAll, $i$a$-synchronized-Http2Connection$ReaderRunnable$ping$1 = 0;
/*      */           switch (payload1) {
/*      */             case 1:
/*      */               Http2Connection.this.intervalPongsReceived = (l = Http2Connection.this.intervalPongsReceived) + 1L;
/*      */             case 2:
/*      */               Http2Connection.this.degradedPongsReceived = (l = Http2Connection.this.degradedPongsReceived) + 1L;
/*      */             case 3:
/*      */               Http2Connection.this.awaitPongsReceived = (l = Http2Connection.this.awaitPongsReceived) + 1L;
/*      */               $this$notifyAll$iv = Http2Connection.this;
/*      */               $i$f$notifyAll = 0;
/*      */               if ($this$notifyAll$iv == null)
/*      */                 throw new NullPointerException("null cannot be cast to non-null type java.lang.Object"); 
/*      */               $this$notifyAll$iv.notifyAll();
/*      */             default:
/*      */               break;
/*      */           } 
/*      */           Unit unit = Unit.INSTANCE;
/*      */           /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */
/*      */         } 
/*      */       } else {
/*      */         TaskQueue taskQueue = Http2Connection.this.writerQueue;
/*      */         String name$iv = Http2Connection.this.getConnectionName$okhttp() + " ping";
/*      */         long delayNanos$iv = 0L;
/*      */         boolean cancelable$iv = true;
/*      */         int $i$f$execute = 0;
/*      */         taskQueue.schedule(new Http2Connection$ReaderRunnable$ping$$inlined$execute$1(name$iv, cancelable$iv, name$iv, cancelable$iv, this, payload1, payload2), delayNanos$iv);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void goAway(int lastGoodStreamId, @NotNull ErrorCode errorCode, @NotNull ByteString debugData) {
/*      */       Intrinsics.checkNotNullParameter(errorCode, "errorCode");
/*      */       Intrinsics.checkNotNullParameter(debugData, "debugData");
/*      */       if (debugData.size() > 0);
/*      */       Http2Stream[] streamsCopy = null;
/*      */       Http2Connection http2Connection = Http2Connection.this;
/*      */       null = 0;
/*      */       synchronized (false) {
/*      */         int $i$a$-synchronized-Http2Connection$ReaderRunnable$goAway$1 = 0;
/*      */         Collection<Http2Stream> $this$toTypedArray$iv = Http2Connection.this.getStreams$okhttp().values();
/*      */         int $i$f$toTypedArray = 0;
/*      */         Collection<Http2Stream> thisCollection$iv = $this$toTypedArray$iv;
/*      */         if (thisCollection$iv.toArray(new Http2Stream[0]) == null)
/*      */           throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>"); 
/*      */         streamsCopy = thisCollection$iv.toArray(new Http2Stream[0]);
/*      */         Http2Connection.this.isShutdown = true;
/*      */         Unit unit = Unit.INSTANCE;
/*      */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */
/*      */       } 
/*      */       for (Http2Stream http2Stream : streamsCopy) {
/*      */         if (http2Stream.getId() > lastGoodStreamId && http2Stream.isLocallyInitiated()) {
/*      */           http2Stream.receiveRstStream(ErrorCode.REFUSED_STREAM);
/*      */           Http2Connection.this.removeStream$okhttp(http2Stream.getId());
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public void windowUpdate(int streamId, long windowSizeIncrement) {
/*      */       if (streamId == 0) {
/*      */         Http2Connection http2Connection = Http2Connection.this;
/*      */         boolean bool = false;
/*      */         synchronized (false) {
/*      */           int $i$a$-synchronized-Http2Connection$ReaderRunnable$windowUpdate$1 = 0;
/*      */           Http2Connection.this.writeBytesMaximum = Http2Connection.this.getWriteBytesMaximum() + windowSizeIncrement;
/*      */           Object $this$notifyAll$iv = Http2Connection.this;
/*      */           int $i$f$notifyAll = 0;
/*      */           if ($this$notifyAll$iv == null)
/*      */             throw new NullPointerException("null cannot be cast to non-null type java.lang.Object"); 
/*      */           $this$notifyAll$iv.notifyAll();
/*      */           Unit unit = Unit.INSTANCE;
/*      */           /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */
/*      */         } 
/*      */       } else {
/*      */         Http2Stream stream = Http2Connection.this.getStream(streamId);
/*      */         if (stream != null) {
/*      */           boolean bool = false;
/*      */           synchronized (false) {
/*      */             int $i$a$-synchronized-Http2Connection$ReaderRunnable$windowUpdate$2 = 0;
/*      */             stream.addBytesToWriteWindow(windowSizeIncrement);
/*      */             Unit unit = Unit.INSTANCE;
/*      */             /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Stream}, name=stream} */
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public void priority(int streamId, int streamDependency, int weight, boolean exclusive) {}
/*      */     
/*      */     public void pushPromise(int streamId, int promisedStreamId, @NotNull List<Header> requestHeaders) {
/*      */       Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
/*      */       Http2Connection.this.pushRequestLater$okhttp(promisedStreamId, requestHeaders);
/*      */     }
/*      */     
/*      */     public void alternateService(int streamId, @NotNull String origin, @NotNull ByteString protocol, @NotNull String host, int port, long maxAge) {
/*      */       Intrinsics.checkNotNullParameter(origin, "origin");
/*      */       Intrinsics.checkNotNullParameter(protocol, "protocol");
/*      */       Intrinsics.checkNotNullParameter(host, "host");
/*      */     }
/*      */   }
/*      */   
/*      */   public final boolean pushedStream$okhttp(int streamId) {
/*      */     return (streamId != 0 && (streamId & 0x1) == 0);
/*      */   }
/*      */   
/*      */   public final void pushRequestLater$okhttp(int streamId, @NotNull List requestHeaders) {
/*      */     Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
/*      */     Http2Connection http2Connection = this;
/*      */     boolean bool = false;
/*      */     synchronized (false) {
/*      */       int $i$a$-synchronized-Http2Connection$pushRequestLater$1 = 0;
/*      */       if (this.currentPushRequests.contains(Integer.valueOf(streamId))) {
/*      */         writeSynResetLater$okhttp(streamId, ErrorCode.PROTOCOL_ERROR);
/*      */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */
/*      */         return;
/*      */       } 
/*      */       null = this.currentPushRequests.add(Integer.valueOf(streamId));
/*      */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/internal/http2/Http2Connection}, name=null} */
/*      */     } 
/*      */     TaskQueue taskQueue = this.pushQueue;
/*      */     String name$iv = this.connectionName + '[' + streamId + "] onRequest";
/*      */     long delayNanos$iv = 0L;
/*      */     boolean cancelable$iv = true;
/*      */     int $i$f$execute = 0;
/*      */     taskQueue.schedule(new Http2Connection$pushRequestLater$$inlined$execute$1(name$iv, cancelable$iv, name$iv, cancelable$iv, this, streamId, requestHeaders), delayNanos$iv);
/*      */   }
/*      */   
/*      */   public final void pushHeadersLater$okhttp(int streamId, @NotNull List requestHeaders, boolean inFinished) {
/*      */     Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
/*      */     TaskQueue taskQueue = this.pushQueue;
/*      */     String name$iv = this.connectionName + '[' + streamId + "] onHeaders";
/*      */     long delayNanos$iv = 0L;
/*      */     boolean cancelable$iv = true;
/*      */     int $i$f$execute = 0;
/*      */     taskQueue.schedule(new Http2Connection$pushHeadersLater$$inlined$execute$1(name$iv, cancelable$iv, name$iv, cancelable$iv, this, streamId, requestHeaders, inFinished), delayNanos$iv);
/*      */   }
/*      */   
/*      */   public final void pushDataLater$okhttp(int streamId, @NotNull BufferedSource source, int byteCount, boolean inFinished) throws IOException {
/*      */     Intrinsics.checkNotNullParameter(source, "source");
/*      */     Buffer buffer = new Buffer();
/*      */     source.require(byteCount);
/*      */     source.read(buffer, byteCount);
/*      */     TaskQueue taskQueue = this.pushQueue;
/*      */     String name$iv = this.connectionName + '[' + streamId + "] onData";
/*      */     long delayNanos$iv = 0L;
/*      */     boolean cancelable$iv = true;
/*      */     int $i$f$execute = 0;
/*      */     taskQueue.schedule(new Http2Connection$pushDataLater$$inlined$execute$1(name$iv, cancelable$iv, name$iv, cancelable$iv, this, streamId, buffer, byteCount, inFinished), delayNanos$iv);
/*      */   }
/*      */   
/*      */   public final void pushResetLater$okhttp(int streamId, @NotNull ErrorCode errorCode) {
/*      */     Intrinsics.checkNotNullParameter(errorCode, "errorCode");
/*      */     TaskQueue taskQueue = this.pushQueue;
/*      */     String name$iv = this.connectionName + '[' + streamId + "] onReset";
/*      */     long delayNanos$iv = 0L;
/*      */     boolean cancelable$iv = true;
/*      */     int $i$f$execute = 0;
/*      */     taskQueue.schedule(new Http2Connection$pushResetLater$$inlined$execute$1(name$iv, cancelable$iv, name$iv, cancelable$iv, this, streamId, errorCode), delayNanos$iv);
/*      */   }
/*      */   
/*      */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000(\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\b&\030\000 \f2\0020\001:\001\fB\005¢\006\002\020\002J\030\020\003\032\0020\0042\006\020\005\032\0020\0062\006\020\007\032\0020\bH\026J\020\020\t\032\0020\0042\006\020\n\032\0020\013H&¨\006\r"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$Listener;", "", "()V", "onSettings", "", "connection", "Lokhttp3/internal/http2/Http2Connection;", "settings", "Lokhttp3/internal/http2/Settings;", "onStream", "stream", "Lokhttp3/internal/http2/Http2Stream;", "Companion", "okhttp"})
/*      */   public static abstract class Listener {
/*      */     @JvmField
/*      */     @NotNull
/*      */     public static final Listener REFUSE_INCOMING_STREAMS = new Http2Connection$Listener$Companion$REFUSE_INCOMING_STREAMS$1();
/*      */     
/*      */     public abstract void onStream(@NotNull Http2Stream param1Http2Stream) throws IOException;
/*      */     
/*      */     public void onSettings(@NotNull Http2Connection connection, @NotNull Settings settings) {
/*      */       Intrinsics.checkNotNullParameter(connection, "connection");
/*      */       Intrinsics.checkNotNullParameter(settings, "settings");
/*      */     }
/*      */     
/*      */     @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\022\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\020\020\003\032\0020\0048\006X\004¢\006\002\n\000¨\006\005"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$Listener$Companion;", "", "()V", "REFUSE_INCOMING_STREAMS", "Lokhttp3/internal/http2/Http2Connection$Listener;", "okhttp"})
/*      */     public static final class Companion {
/*      */       private Companion() {}
/*      */     }
/*      */     
/*      */     public static final Companion Companion = new Companion(null);
/*      */     
/*      */     static {
/*      */     
/*      */     }
/*      */     
/*      */     @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\027\n\000\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\000*\001\000\b\n\030\0002\0020\001J\020\020\002\032\0020\0032\006\020\004\032\0020\005H\026¨\006\006"}, d2 = {"okhttp3/internal/http2/Http2Connection$Listener$Companion$REFUSE_INCOMING_STREAMS$1", "Lokhttp3/internal/http2/Http2Connection$Listener;", "onStream", "", "stream", "Lokhttp3/internal/http2/Http2Stream;", "okhttp"})
/*      */     public static final class Http2Connection$Listener$Companion$REFUSE_INCOMING_STREAMS$1 extends Listener {
/*      */       public void onStream(@NotNull Http2Stream stream) throws IOException {
/*      */         Intrinsics.checkNotNullParameter(stream, "stream");
/*      */         stream.close(ErrorCode.REFUSED_STREAM, null);
/*      */       }
/*      */     }
/*      */   }
/*      */   public static final Companion Companion = new Companion(null);
/*      */   
/*      */   static {
/*      */     Settings settings1 = new Settings();
/*      */     boolean bool1 = false, bool2 = false;
/*      */     Settings $this$apply = settings1;
/*      */     int $i$a$-apply-Http2Connection$Companion$DEFAULT_SETTINGS$1 = 0;
/*      */     $this$apply.set(7, 65535);
/*      */     $this$apply.set(5, 16384);
/*      */     DEFAULT_SETTINGS = settings1;
/*      */   }
/*      */   
/*      */   @JvmOverloads
/*      */   public final void start(boolean sendConnectionPreface) throws IOException {
/*      */     start$default(this, sendConnectionPreface, null, 2, null);
/*      */   }
/*      */   
/*      */   @JvmOverloads
/*      */   public final void start() throws IOException {
/*      */     start$default(this, false, null, 3, null);
/*      */   }
/*      */   
/*      */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\000\n\002\030\002\n\002\b\007\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\016\020\003\032\0020\004XT¢\006\002\n\000R\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\007\020\bR\016\020\t\032\0020\004XT¢\006\002\n\000R\016\020\n\032\0020\004XT¢\006\002\n\000R\016\020\013\032\0020\004XT¢\006\002\n\000R\016\020\f\032\0020\004XT¢\006\002\n\000¨\006\r"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$Companion;", "", "()V", "AWAIT_PING", "", "DEFAULT_SETTINGS", "Lokhttp3/internal/http2/Settings;", "getDEFAULT_SETTINGS", "()Lokhttp3/internal/http2/Settings;", "DEGRADED_PING", "DEGRADED_PONG_TIMEOUT_NS", "INTERVAL_PING", "OKHTTP_CLIENT_WINDOW_SIZE", "okhttp"})
/*      */   public static final class Companion {
/*      */     private Companion() {}
/*      */     
/*      */     @NotNull
/*      */     public final Settings getDEFAULT_SETTINGS() {
/*      */       return Http2Connection.DEFAULT_SETTINGS;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http2/Http2Connection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */