/*     */ package okhttp3;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.nio.charset.Charset;
/*     */ import kotlin.Deprecated;
/*     */ import kotlin.DeprecationLevel;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.ReplaceWith;
/*     */ import kotlin.io.CloseableKt;
/*     */ import kotlin.jvm.JvmName;
/*     */ import kotlin.jvm.JvmStatic;
/*     */ import kotlin.jvm.functions.Function1;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.InlineMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.text.Charsets;
/*     */ import okhttp3.internal.Util;
/*     */ import okio.Buffer;
/*     */ import okio.BufferedSource;
/*     */ import okio.ByteString;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000b\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\022\n\002\b\002\n\002\030\002\n\000\n\002\020\002\n\002\b\002\n\002\020\000\n\000\n\002\030\002\n\002\030\002\n\000\n\002\020\b\n\002\b\002\n\002\020\t\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\003\b&\030\000 !2\0020\001:\002 !B\005¢\006\002\020\002J\006\020\005\032\0020\006J\006\020\007\032\0020\bJ\006\020\t\032\0020\nJ\006\020\013\032\0020\004J\b\020\f\032\0020\rH\002J\b\020\016\032\0020\017H\026J@\020\020\032\002H\021\"\b\b\000\020\021*\0020\0222\022\020\023\032\016\022\004\022\0020\025\022\004\022\002H\0210\0242\022\020\026\032\016\022\004\022\002H\021\022\004\022\0020\0270\024H\b¢\006\002\020\030J\b\020\031\032\0020\032H&J\n\020\033\032\004\030\0010\034H&J\b\020\035\032\0020\025H&J\006\020\036\032\0020\037R\020\020\003\032\004\030\0010\004X\016¢\006\002\n\000¨\006\""}, d2 = {"Lokhttp3/ResponseBody;", "Ljava/io/Closeable;", "()V", "reader", "Ljava/io/Reader;", "byteStream", "Ljava/io/InputStream;", "byteString", "Lokio/ByteString;", "bytes", "", "charStream", "charset", "Ljava/nio/charset/Charset;", "close", "", "consumeSource", "T", "", "consumer", "Lkotlin/Function1;", "Lokio/BufferedSource;", "sizeMapper", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "source", "string", "", "BomAwareReader", "Companion", "okhttp"})
/*     */ public abstract class ResponseBody
/*     */   implements Closeable
/*     */ {
/*     */   private Reader reader;
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   @Nullable
/*     */   public abstract MediaType contentType();
/*     */   
/*     */   public abstract long contentLength();
/*     */   
/*     */   @NotNull
/*     */   public final InputStream byteStream() {
/* 112 */     return source().inputStream();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public abstract BufferedSource source();
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public final byte[] bytes() throws IOException {
/* 124 */     ResponseBody this_$iv = this; int $i$f$consumeSource = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 322 */     long contentLength$iv = this_$iv.contentLength();
/* 323 */     if (contentLength$iv > 2147483647L) {
/* 324 */       throw (Throwable)new IOException("Cannot buffer entire body for content length: " + contentLength$iv);
/*     */     }
/*     */     
/* 327 */     Closeable closeable = (Closeable)this_$iv.source(); boolean bool1 = false, bool2 = false; Throwable throwable = (Throwable)null; try { BufferedSource p1 = (BufferedSource)closeable; int $i$a$-unknown-ResponseBody$bytes$1 = 0; byte[] arrayOfByte = p1.readByteArray(); } catch (Throwable throwable1) { throwable = throwable1 = null; throw throwable1; } finally { CloseableKt.closeFinally(closeable, throwable); }  Object bytes$iv = arrayOfByte;
/* 328 */     Object object1 = bytes$iv; int $i$a$-consumeSource-ResponseBody$bytes$2 = 0; int size$iv = object1.length;
/* 329 */     if (contentLength$iv != -1L && contentLength$iv != size$iv) {
/* 330 */       throw (Throwable)new IOException("Content-Length (" + contentLength$iv + ") and stream length (" + size$iv + ") disagree");
/*     */     }
/* 332 */     return (byte[])bytes$iv; } @NotNull public final ByteString byteString() throws IOException { ResponseBody this_$iv = this; int $i$f$consumeSource = 0;
/* 333 */     long contentLength$iv = this_$iv.contentLength();
/* 334 */     if (contentLength$iv > 2147483647L) {
/* 335 */       throw (Throwable)new IOException("Cannot buffer entire body for content length: " + contentLength$iv);
/*     */     }
/*     */     
/* 338 */     Closeable closeable = (Closeable)this_$iv.source(); boolean bool1 = false, bool2 = false; Throwable throwable = (Throwable)null; try { BufferedSource p1 = (BufferedSource)closeable; int $i$a$-unknown-ResponseBody$byteString$1 = 0; ByteString byteString = p1.readByteString(); } catch (Throwable throwable1) { throwable = throwable1 = null; throw throwable1; } finally { CloseableKt.closeFinally(closeable, throwable); }  Object bytes$iv = byteString;
/* 339 */     Object object1 = bytes$iv; int $i$a$-consumeSource-ResponseBody$byteString$2 = 0; int size$iv = object1.size();
/* 340 */     if (contentLength$iv != -1L && contentLength$iv != size$iv) {
/* 341 */       throw (Throwable)new IOException("Content-Length (" + contentLength$iv + ") and stream length (" + size$iv + ") disagree");
/*     */     }
/* 343 */     return (ByteString)bytes$iv; }
/*     */ 
/*     */   
/*     */   private final <T> T consumeSource(Function1 consumer, Function1 sizeMapper) {
/*     */     int $i$f$consumeSource = 0;
/*     */     long contentLength = contentLength();
/*     */     if (contentLength > 2147483647L)
/*     */       throw (Throwable)new IOException("Cannot buffer entire body for content length: " + contentLength); 
/*     */     Closeable closeable = (Closeable)source();
/*     */     boolean bool1 = false, bool2 = false;
/*     */     Throwable throwable = (Throwable)null;
/*     */     try {
/*     */       Object object = consumer.invoke(closeable);
/*     */     } catch (Throwable throwable1) {
/*     */       throwable = throwable1 = null;
/*     */       throw throwable1;
/*     */     } finally {
/*     */       InlineMarker.finallyStart(1);
/*     */       CloseableKt.closeFinally(closeable, throwable);
/*     */       InlineMarker.finallyEnd(1);
/*     */     } 
/*     */     Object bytes = object;
/*     */     int size = ((Number)sizeMapper.invoke(bytes)).intValue();
/*     */     if (contentLength != -1L && contentLength != size)
/*     */       throw (Throwable)new IOException("Content-Length (" + contentLength + ") and stream length (" + size + ") disagree"); 
/*     */     return (T)bytes;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public final Reader charStream() {
/*     */     if (this.reader == null) {
/*     */       BomAwareReader bomAwareReader1 = new BomAwareReader(source(), charset());
/*     */       boolean bool1 = false, bool2 = false;
/*     */       BomAwareReader it = bomAwareReader1;
/*     */       int $i$a$-also-ResponseBody$charStream$1 = 0;
/*     */       this.reader = it;
/*     */     } 
/*     */     return bomAwareReader1;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public final String string() throws IOException {
/*     */     Closeable closeable = (Closeable)source();
/*     */     boolean bool1 = false, bool2 = false;
/*     */     Throwable throwable = (Throwable)null;
/*     */     try {
/*     */       BufferedSource source = (BufferedSource)closeable;
/*     */       int $i$a$-use-ResponseBody$string$1 = 0;
/*     */       String str = source.readString(Util.readBomAsCharset(source, charset()));
/*     */     } catch (Throwable throwable1) {
/*     */       throwable = throwable1 = null;
/*     */       throw throwable1;
/*     */     } finally {
/*     */       CloseableKt.closeFinally(closeable, throwable);
/*     */     } 
/*     */     return str;
/*     */   }
/*     */   
/*     */   private final Charset charset() {
/*     */     if (contentType() == null || contentType().charset(Charsets.UTF_8) == null)
/*     */       contentType().charset(Charsets.UTF_8); 
/*     */     return Charsets.UTF_8;
/*     */   }
/*     */   
/*     */   public void close() {
/*     */     Util.closeQuietly((Closeable)source());
/*     */   }
/*     */   
/*     */   @JvmStatic
/*     */   @JvmName(name = "create")
/*     */   @NotNull
/*     */   public static final ResponseBody create(@NotNull String $this$toResponseBody, @Nullable MediaType contentType) {
/*     */     return Companion.create($this$toResponseBody, contentType);
/*     */   }
/*     */   
/*     */   @JvmStatic
/*     */   @JvmName(name = "create")
/*     */   @NotNull
/*     */   public static final ResponseBody create(@NotNull byte[] $this$toResponseBody, @Nullable MediaType contentType) {
/*     */     return Companion.create($this$toResponseBody, contentType);
/*     */   }
/*     */   
/*     */   @JvmStatic
/*     */   @JvmName(name = "create")
/*     */   @NotNull
/*     */   public static final ResponseBody create(@NotNull ByteString $this$toResponseBody, @Nullable MediaType contentType) {
/*     */     return Companion.create($this$toResponseBody, contentType);
/*     */   }
/*     */   
/*     */   @JvmStatic
/*     */   @JvmName(name = "create")
/*     */   @NotNull
/*     */   public static final ResponseBody create(@NotNull BufferedSource $this$asResponseBody, @Nullable MediaType contentType, long contentLength) {
/*     */     return Companion.create($this$asResponseBody, contentType, contentLength);
/*     */   }
/*     */   
/*     */   @JvmStatic
/*     */   @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}, expression = "content.toResponseBody(contentType)"), level = DeprecationLevel.WARNING)
/*     */   @NotNull
/*     */   public static final ResponseBody create(@Nullable MediaType contentType, @NotNull String content) {
/*     */     return Companion.create(contentType, content);
/*     */   }
/*     */   
/*     */   @JvmStatic
/*     */   @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}, expression = "content.toResponseBody(contentType)"), level = DeprecationLevel.WARNING)
/*     */   @NotNull
/*     */   public static final ResponseBody create(@Nullable MediaType contentType, @NotNull byte[] content) {
/*     */     return Companion.create(contentType, content);
/*     */   }
/*     */   
/*     */   @JvmStatic
/*     */   @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}, expression = "content.toResponseBody(contentType)"), level = DeprecationLevel.WARNING)
/*     */   @NotNull
/*     */   public static final ResponseBody create(@Nullable MediaType contentType, @NotNull ByteString content) {
/*     */     return Companion.create(contentType, content);
/*     */   }
/*     */   
/*     */   @JvmStatic
/*     */   @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.ResponseBody.Companion.asResponseBody"}, expression = "content.asResponseBody(contentType, contentLength)"), level = DeprecationLevel.WARNING)
/*     */   @NotNull
/*     */   public static final ResponseBody create(@Nullable MediaType contentType, long contentLength, @NotNull BufferedSource content) {
/*     */     return Companion.create(contentType, contentLength, content);
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0004\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\002\n\002\020\002\n\000\n\002\020\b\n\000\n\002\020\031\n\002\b\003\b\000\030\0002\0020\001B\025\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005¢\006\002\020\006J\b\020\n\032\0020\013H\026J \020\f\032\0020\r2\006\020\016\032\0020\0172\006\020\020\032\0020\r2\006\020\021\032\0020\rH\026R\016\020\004\032\0020\005X\004¢\006\002\n\000R\016\020\007\032\0020\bX\016¢\006\002\n\000R\020\020\t\032\004\030\0010\001X\016¢\006\002\n\000R\016\020\002\032\0020\003X\004¢\006\002\n\000¨\006\022"}, d2 = {"Lokhttp3/ResponseBody$BomAwareReader;", "Ljava/io/Reader;", "source", "Lokio/BufferedSource;", "charset", "Ljava/nio/charset/Charset;", "(Lokio/BufferedSource;Ljava/nio/charset/Charset;)V", "closed", "", "delegate", "close", "", "read", "", "cbuf", "", "off", "len", "okhttp"})
/*     */   public static final class BomAwareReader extends Reader {
/*     */     private boolean closed;
/*     */     private Reader delegate;
/*     */     private final BufferedSource source;
/*     */     private final Charset charset;
/*     */     
/*     */     public BomAwareReader(@NotNull BufferedSource source, @NotNull Charset charset) {
/*     */       this.source = source;
/*     */       this.charset = charset;
/*     */     }
/*     */     
/*     */     public int read(@NotNull char[] cbuf, int off, int len) throws IOException {
/*     */       Intrinsics.checkNotNullParameter(cbuf, "cbuf");
/*     */       if (this.closed)
/*     */         throw (Throwable)new IOException("Stream closed"); 
/*     */       if (this.delegate == null) {
/*     */         InputStreamReader inputStreamReader1 = new InputStreamReader(this.source.inputStream(), Util.readBomAsCharset(this.source, this.charset));
/*     */         boolean bool1 = false, bool2 = false;
/*     */         InputStreamReader it = inputStreamReader1;
/*     */         int $i$a$-also-ResponseBody$BomAwareReader$read$finalDelegate$1 = 0;
/*     */         this.delegate = it;
/*     */       } 
/*     */       Reader finalDelegate = inputStreamReader1;
/*     */       return finalDelegate.read(cbuf, off, len);
/*     */     }
/*     */     
/*     */     public void close() throws IOException {
/*     */       this.closed = true;
/*     */       if (this.delegate != null) {
/*     */         this.delegate.close();
/*     */       } else {
/*     */         BomAwareReader bomAwareReader1 = this;
/*     */         boolean bool1 = false, bool2 = false;
/*     */         BomAwareReader $this$run = bomAwareReader1;
/*     */         int $i$a$-run-ResponseBody$BomAwareReader$close$1 = 0;
/*     */         $this$run.source.close();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0002\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\022\n\000\n\002\020\t\n\002\030\002\n\002\020\016\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\032\020\003\032\0020\0042\b\020\005\032\004\030\0010\0062\006\020\007\032\0020\bH\007J\"\020\003\032\0020\0042\b\020\005\032\004\030\0010\0062\006\020\t\032\0020\n2\006\020\007\032\0020\013H\007J\032\020\003\032\0020\0042\b\020\005\032\004\030\0010\0062\006\020\007\032\0020\fH\007J\032\020\003\032\0020\0042\b\020\005\032\004\030\0010\0062\006\020\007\032\0020\rH\007J'\020\016\032\0020\004*\0020\0132\n\b\002\020\005\032\004\030\0010\0062\b\b\002\020\t\032\0020\nH\007¢\006\002\b\003J\035\020\017\032\0020\004*\0020\b2\n\b\002\020\005\032\004\030\0010\006H\007¢\006\002\b\003J\035\020\017\032\0020\004*\0020\f2\n\b\002\020\005\032\004\030\0010\006H\007¢\006\002\b\003J\035\020\017\032\0020\004*\0020\r2\n\b\002\020\005\032\004\030\0010\006H\007¢\006\002\b\003¨\006\020"}, d2 = {"Lokhttp3/ResponseBody$Companion;", "", "()V", "create", "Lokhttp3/ResponseBody;", "contentType", "Lokhttp3/MediaType;", "content", "", "contentLength", "", "Lokio/BufferedSource;", "", "Lokio/ByteString;", "asResponseBody", "toResponseBody", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */     
/*     */     @JvmStatic
/*     */     @JvmName(name = "create")
/*     */     @NotNull
/*     */     public final ResponseBody create(@NotNull String $this$toResponseBody, @Nullable MediaType contentType) {
/*     */       Intrinsics.checkNotNullParameter($this$toResponseBody, "$this$toResponseBody");
/*     */       Charset charset = Charsets.UTF_8;
/*     */       MediaType finalContentType = contentType;
/*     */       if (contentType != null) {
/*     */         Charset resolvedCharset = MediaType.charset$default(contentType, null, 1, null);
/*     */         if (resolvedCharset == null) {
/*     */           charset = Charsets.UTF_8;
/*     */           finalContentType = MediaType.Companion.parse(contentType + "; charset=utf-8");
/*     */         } else {
/*     */           charset = resolvedCharset;
/*     */         } 
/*     */       } 
/*     */       Buffer buffer = (new Buffer()).writeString($this$toResponseBody, charset);
/*     */       return create((BufferedSource)buffer, finalContentType, buffer.size());
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @JvmName(name = "create")
/*     */     @NotNull
/*     */     public final ResponseBody create(@NotNull byte[] $this$toResponseBody, @Nullable MediaType contentType) {
/*     */       Intrinsics.checkNotNullParameter($this$toResponseBody, "$this$toResponseBody");
/*     */       return create((BufferedSource)(new Buffer()).write($this$toResponseBody), contentType, $this$toResponseBody.length);
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @JvmName(name = "create")
/*     */     @NotNull
/*     */     public final ResponseBody create(@NotNull ByteString $this$toResponseBody, @Nullable MediaType contentType) {
/*     */       Intrinsics.checkNotNullParameter($this$toResponseBody, "$this$toResponseBody");
/*     */       return create((BufferedSource)(new Buffer()).write($this$toResponseBody), contentType, $this$toResponseBody.size());
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @JvmName(name = "create")
/*     */     @NotNull
/*     */     public final ResponseBody create(@NotNull BufferedSource $this$asResponseBody, @Nullable MediaType contentType, long contentLength) {
/*     */       Intrinsics.checkNotNullParameter($this$asResponseBody, "$this$asResponseBody");
/*     */       return new ResponseBody$Companion$asResponseBody$1($this$asResponseBody, contentType, contentLength);
/*     */     }
/*     */     
/*     */     @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\035\n\000\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\000\n\002\030\002\n\000*\001\000\b\n\030\0002\0020\001J\b\020\002\032\0020\003H\026J\n\020\004\032\004\030\0010\005H\026J\b\020\006\032\0020\007H\026¨\006\b"}, d2 = {"okhttp3/ResponseBody$Companion$asResponseBody$1", "Lokhttp3/ResponseBody;", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "source", "Lokio/BufferedSource;", "okhttp"})
/*     */     public static final class ResponseBody$Companion$asResponseBody$1 extends ResponseBody {
/*     */       ResponseBody$Companion$asResponseBody$1(BufferedSource $receiver, MediaType $captured_local_variable$1, long $captured_local_variable$2) {}
/*     */       
/*     */       @Nullable
/*     */       public MediaType contentType() {
/*     */         return this.$contentType;
/*     */       }
/*     */       
/*     */       public long contentLength() {
/*     */         return this.$contentLength;
/*     */       }
/*     */       
/*     */       @NotNull
/*     */       public BufferedSource source() {
/*     */         return this.$this_asResponseBody;
/*     */       }
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}, expression = "content.toResponseBody(contentType)"), level = DeprecationLevel.WARNING)
/*     */     @NotNull
/*     */     public final ResponseBody create(@Nullable MediaType contentType, @NotNull String content) {
/*     */       Intrinsics.checkNotNullParameter(content, "content");
/*     */       return create(content, contentType);
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}, expression = "content.toResponseBody(contentType)"), level = DeprecationLevel.WARNING)
/*     */     @NotNull
/*     */     public final ResponseBody create(@Nullable MediaType contentType, @NotNull byte[] content) {
/*     */       Intrinsics.checkNotNullParameter(content, "content");
/*     */       return create(content, contentType);
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}, expression = "content.toResponseBody(contentType)"), level = DeprecationLevel.WARNING)
/*     */     @NotNull
/*     */     public final ResponseBody create(@Nullable MediaType contentType, @NotNull ByteString content) {
/*     */       Intrinsics.checkNotNullParameter(content, "content");
/*     */       return create(content, contentType);
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.ResponseBody.Companion.asResponseBody"}, expression = "content.asResponseBody(contentType, contentLength)"), level = DeprecationLevel.WARNING)
/*     */     @NotNull
/*     */     public final ResponseBody create(@Nullable MediaType contentType, long contentLength, @NotNull BufferedSource content) {
/*     */       Intrinsics.checkNotNullParameter(content, "content");
/*     */       return create(content, contentType, contentLength);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/ResponseBody.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */