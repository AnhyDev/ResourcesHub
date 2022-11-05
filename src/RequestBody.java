/*     */ package okhttp3;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.charset.Charset;
/*     */ import kotlin.Deprecated;
/*     */ import kotlin.DeprecationLevel;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.ReplaceWith;
/*     */ import kotlin.io.CloseableKt;
/*     */ import kotlin.jvm.JvmName;
/*     */ import kotlin.jvm.JvmOverloads;
/*     */ import kotlin.jvm.JvmStatic;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.text.Charsets;
/*     */ import okhttp3.internal.Util;
/*     */ import okio.BufferedSink;
/*     */ import okio.ByteString;
/*     */ import okio.Okio;
/*     */ import okio.Source;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000.\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\t\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\002\b\002\b&\030\000 \0162\0020\001:\001\016B\005¢\006\002\020\002J\b\020\003\032\0020\004H\026J\n\020\005\032\004\030\0010\006H&J\b\020\007\032\0020\bH\026J\b\020\t\032\0020\bH\026J\020\020\n\032\0020\0132\006\020\f\032\0020\rH&¨\006\017"}, d2 = {"Lokhttp3/RequestBody;", "", "()V", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "isDuplex", "", "isOneShot", "writeTo", "", "sink", "Lokio/BufferedSink;", "Companion", "okhttp"})
/*     */ public abstract class RequestBody
/*     */ {
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   @Nullable
/*     */   public abstract MediaType contentType();
/*     */   
/*     */   public long contentLength() throws IOException {
/*  38 */     return -1L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void writeTo(@NotNull BufferedSink paramBufferedSink) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDuplex() {
/*  76 */     return false;
/*     */   }
/*     */   @JvmStatic @JvmName(name = "create") @NotNull public static final RequestBody create(@NotNull String $this$toRequestBody, @Nullable MediaType contentType) { return Companion.create($this$toRequestBody, contentType); }
/*     */   @JvmStatic @JvmName(name = "create") @NotNull public static final RequestBody create(@NotNull ByteString $this$toRequestBody, @Nullable MediaType contentType) { return Companion.create($this$toRequestBody, contentType); }
/*     */   @JvmStatic @JvmOverloads @JvmName(name = "create") @NotNull public static final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType, int offset, int byteCount) { return Companion.create($this$toRequestBody, contentType, offset, byteCount); }
/*     */   @JvmStatic @JvmOverloads @JvmName(name = "create") @NotNull public static final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType, int offset) { return Companion.create$default(Companion, $this$toRequestBody, contentType, offset, 0, 4, (Object)null); }
/*     */   @JvmStatic @JvmOverloads @JvmName(name = "create") @NotNull public static final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType) { return Companion.create$default(Companion, $this$toRequestBody, contentType, 0, 0, 6, (Object)null); }
/*     */   @JvmStatic @JvmOverloads @JvmName(name = "create") @NotNull public static final RequestBody create(@NotNull byte[] $this$toRequestBody) { return Companion.create$default(Companion, $this$toRequestBody, (MediaType)null, 0, 0, 7, (Object)null); }
/*     */   @JvmStatic @JvmName(name = "create") @NotNull public static final RequestBody create(@NotNull File $this$asRequestBody, @Nullable MediaType contentType) { return Companion.create($this$asRequestBody, contentType); }
/*     */   @JvmStatic @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType)"), level = DeprecationLevel.WARNING)
/*     */   @NotNull
/*     */   public static final RequestBody create(@Nullable MediaType contentType, @NotNull String content) { return Companion.create(contentType, content); }
/*     */   @JvmStatic
/*     */   @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType)"), level = DeprecationLevel.WARNING)
/*     */   @NotNull
/*     */   public static final RequestBody create(@Nullable MediaType contentType, @NotNull ByteString content) { return Companion.create(contentType, content); } @JvmStatic
/*     */   @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType, offset, byteCount)"), level = DeprecationLevel.WARNING)
/*     */   @JvmOverloads
/*     */   @NotNull
/*  95 */   public static final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content, int offset, int byteCount) { return Companion.create(contentType, content, offset, byteCount); } public boolean isOneShot() { return false; }
/*     */   @JvmStatic @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType, offset, byteCount)"), level = DeprecationLevel.WARNING) @JvmOverloads @NotNull public static final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content, int offset) { return Companion.create$default(Companion, contentType, content, offset, 0, 8, (Object)null); } @JvmStatic @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType, offset, byteCount)"), level = DeprecationLevel.WARNING) @JvmOverloads @NotNull
/*     */   public static final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content) { return Companion.create$default(Companion, contentType, content, 0, 0, 12, (Object)null); } @JvmStatic
/*     */   @Deprecated(message = "Moved to extension function. Put the 'file' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.asRequestBody"}, expression = "file.asRequestBody(contentType)"), level = DeprecationLevel.WARNING)
/*     */   @NotNull
/*     */   public static final RequestBody create(@Nullable MediaType contentType, @NotNull File file) { return Companion.create(contentType, file); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0006\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\022\n\000\n\002\020\b\n\000\n\002\020\016\n\002\030\002\n\002\b\003\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\032\020\003\032\0020\0042\b\020\005\032\004\030\0010\0062\006\020\007\032\0020\bH\007J.\020\003\032\0020\0042\b\020\005\032\004\030\0010\0062\006\020\t\032\0020\n2\b\b\002\020\013\032\0020\f2\b\b\002\020\r\032\0020\fH\007J\032\020\003\032\0020\0042\b\020\005\032\004\030\0010\0062\006\020\t\032\0020\016H\007J\032\020\003\032\0020\0042\b\020\005\032\004\030\0010\0062\006\020\t\032\0020\017H\007J\035\020\020\032\0020\004*\0020\b2\n\b\002\020\005\032\004\030\0010\006H\007¢\006\002\b\003J1\020\021\032\0020\004*\0020\n2\n\b\002\020\005\032\004\030\0010\0062\b\b\002\020\013\032\0020\f2\b\b\002\020\r\032\0020\fH\007¢\006\002\b\003J\035\020\021\032\0020\004*\0020\0162\n\b\002\020\005\032\004\030\0010\006H\007¢\006\002\b\003J\035\020\021\032\0020\004*\0020\0172\n\b\002\020\005\032\004\030\0010\006H\007¢\006\002\b\003¨\006\022"}, d2 = {"Lokhttp3/RequestBody$Companion;", "", "()V", "create", "Lokhttp3/RequestBody;", "contentType", "Lokhttp3/MediaType;", "file", "Ljava/io/File;", "content", "", "offset", "", "byteCount", "", "Lokio/ByteString;", "asRequestBody", "toRequestBody", "okhttp"})
/*     */   public static final class Companion
/*     */   {
/*     */     @JvmStatic
/*     */     @JvmName(name = "create")
/*     */     @NotNull
/* 106 */     public final RequestBody create(@NotNull String $this$toRequestBody, @Nullable MediaType contentType) { Intrinsics.checkNotNullParameter($this$toRequestBody, "$this$toRequestBody"); Charset charset = Charsets.UTF_8;
/* 107 */       MediaType finalContentType = contentType;
/* 108 */       if (contentType != null) {
/* 109 */         Charset resolvedCharset = MediaType.charset$default(contentType, null, 1, null);
/* 110 */         if (resolvedCharset == null) {
/* 111 */           charset = Charsets.UTF_8;
/* 112 */           finalContentType = MediaType.Companion.parse(contentType + "; charset=utf-8");
/*     */         } else {
/* 114 */           charset = resolvedCharset;
/*     */         } 
/*     */       } 
/* 117 */       String str = $this$toRequestBody; boolean bool = false; Intrinsics.checkNotNullExpressionValue(str.getBytes(charset), "(this as java.lang.String).getBytes(charset)"); byte[] bytes = str.getBytes(charset);
/* 118 */       return create(bytes, finalContentType, 0, bytes.length); }
/*     */     
/*     */     private Companion() {}
/*     */     @JvmStatic
/*     */     @JvmName(name = "create")
/*     */     @NotNull
/*     */     public final RequestBody create(@NotNull ByteString $this$toRequestBody, @Nullable MediaType contentType) {
/* 125 */       Intrinsics.checkNotNullParameter($this$toRequestBody, "$this$toRequestBody"); return new RequestBody$Companion$toRequestBody$1($this$toRequestBody, contentType); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000#\n\000\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\000*\001\000\b\n\030\0002\0020\001J\b\020\002\032\0020\003H\026J\n\020\004\032\004\030\0010\005H\026J\020\020\006\032\0020\0072\006\020\b\032\0020\tH\026¨\006\n"}, d2 = {"okhttp3/RequestBody$Companion$toRequestBody$1", "Lokhttp3/RequestBody;", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "writeTo", "", "sink", "Lokio/BufferedSink;", "okhttp"}) public static final class RequestBody$Companion$toRequestBody$1 extends RequestBody { RequestBody$Companion$toRequestBody$1(ByteString $receiver, MediaType $captured_local_variable$1) {} @Nullable
/* 126 */       public MediaType contentType() { return this.$contentType; }
/*     */        public long contentLength() {
/* 128 */         return this.$this_toRequestBody.size();
/*     */       }
/*     */       public void writeTo(@NotNull BufferedSink sink) {
/* 131 */         Intrinsics.checkNotNullParameter(sink, "sink"); sink.write(this.$this_toRequestBody);
/*     */       } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @JvmStatic
/*     */     @JvmOverloads
/*     */     @JvmName(name = "create")
/*     */     @NotNull
/*     */     public final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType, int offset, int byteCount)
/*     */     {
/* 145 */       Intrinsics.checkNotNullParameter($this$toRequestBody, "$this$toRequestBody"); Util.checkOffsetAndCount($this$toRequestBody.length, offset, byteCount);
/* 146 */       return new RequestBody$Companion$toRequestBody$2($this$toRequestBody, contentType, byteCount, offset); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000#\n\000\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\000*\001\000\b\n\030\0002\0020\001J\b\020\002\032\0020\003H\026J\n\020\004\032\004\030\0010\005H\026J\020\020\006\032\0020\0072\006\020\b\032\0020\tH\026¨\006\n"}, d2 = {"okhttp3/RequestBody$Companion$toRequestBody$2", "Lokhttp3/RequestBody;", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "writeTo", "", "sink", "Lokio/BufferedSink;", "okhttp"}) public static final class RequestBody$Companion$toRequestBody$2 extends RequestBody { RequestBody$Companion$toRequestBody$2(byte[] $receiver, MediaType $captured_local_variable$1, int $captured_local_variable$2, int $captured_local_variable$3) {} @Nullable
/* 147 */       public MediaType contentType() { return this.$contentType; }
/*     */        public long contentLength() {
/* 149 */         return this.$byteCount;
/*     */       }
/*     */       public void writeTo(@NotNull BufferedSink sink) {
/* 152 */         Intrinsics.checkNotNullParameter(sink, "sink"); sink.write(this.$this_toRequestBody, this.$offset, this.$byteCount);
/*     */       } }
/*     */ 
/*     */     
/*     */     @JvmStatic
/*     */     @JvmName(name = "create")
/*     */     @NotNull
/*     */     public final RequestBody create(@NotNull File $this$asRequestBody, @Nullable MediaType contentType)
/*     */     {
/* 161 */       Intrinsics.checkNotNullParameter($this$asRequestBody, "$this$asRequestBody"); return new RequestBody$Companion$asRequestBody$1($this$asRequestBody, contentType); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000#\n\000\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\000*\001\000\b\n\030\0002\0020\001J\b\020\002\032\0020\003H\026J\n\020\004\032\004\030\0010\005H\026J\020\020\006\032\0020\0072\006\020\b\032\0020\tH\026¨\006\n"}, d2 = {"okhttp3/RequestBody$Companion$asRequestBody$1", "Lokhttp3/RequestBody;", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "writeTo", "", "sink", "Lokio/BufferedSink;", "okhttp"}) public static final class RequestBody$Companion$asRequestBody$1 extends RequestBody { RequestBody$Companion$asRequestBody$1(File $receiver, MediaType $captured_local_variable$1) {} @Nullable
/* 162 */       public MediaType contentType() { return this.$contentType; }
/*     */        public long contentLength() {
/* 164 */         return this.$this_asRequestBody.length();
/*     */       }
/*     */       public void writeTo(@NotNull BufferedSink sink) {
/* 167 */         Intrinsics.checkNotNullParameter(sink, "sink"); Closeable closeable = (Closeable)Okio.source(this.$this_asRequestBody); boolean bool1 = false, bool2 = false; Throwable throwable = (Throwable)null; try { Source source = (Source)closeable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 223 */           int $i$a$-use-RequestBody$Companion$asRequestBody$1$writeTo$1 = 0;
/*     */           long l = sink.writeAll(source); }
/*     */         catch (Throwable throwable1)
/*     */         { throwable = throwable1 = null;
/*     */           throw throwable1; }
/*     */         finally
/*     */         { CloseableKt.closeFinally(closeable, throwable); }
/*     */       
/*     */       } }
/*     */ 
/*     */     
/*     */     @JvmStatic
/*     */     @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType)"), level = DeprecationLevel.WARNING)
/*     */     @NotNull
/*     */     public final RequestBody create(@Nullable MediaType contentType, @NotNull String content) {
/*     */       Intrinsics.checkNotNullParameter(content, "content");
/*     */       return create(content, contentType);
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType)"), level = DeprecationLevel.WARNING)
/*     */     @NotNull
/*     */     public final RequestBody create(@Nullable MediaType contentType, @NotNull ByteString content) {
/*     */       Intrinsics.checkNotNullParameter(content, "content");
/*     */       return create(content, contentType);
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType, offset, byteCount)"), level = DeprecationLevel.WARNING)
/*     */     @JvmOverloads
/*     */     @NotNull
/*     */     public final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content, int offset, int byteCount) {
/*     */       Intrinsics.checkNotNullParameter(content, "content");
/*     */       return create(content, contentType, offset, byteCount);
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @Deprecated(message = "Moved to extension function. Put the 'file' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.asRequestBody"}, expression = "file.asRequestBody(contentType)"), level = DeprecationLevel.WARNING)
/*     */     @NotNull
/*     */     public final RequestBody create(@Nullable MediaType contentType, @NotNull File file) {
/*     */       Intrinsics.checkNotNullParameter(file, "file");
/*     */       return create(file, contentType);
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @JvmOverloads
/*     */     @JvmName(name = "create")
/*     */     @NotNull
/*     */     public final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType, int offset) {
/*     */       return create$default(this, $this$toRequestBody, contentType, offset, 0, 4, (Object)null);
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @JvmOverloads
/*     */     @JvmName(name = "create")
/*     */     @NotNull
/*     */     public final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType) {
/*     */       return create$default(this, $this$toRequestBody, contentType, 0, 0, 6, (Object)null);
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @JvmOverloads
/*     */     @JvmName(name = "create")
/*     */     @NotNull
/*     */     public final RequestBody create(@NotNull byte[] $this$toRequestBody) {
/*     */       return create$default(this, $this$toRequestBody, (MediaType)null, 0, 0, 7, (Object)null);
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType, offset, byteCount)"), level = DeprecationLevel.WARNING)
/*     */     @JvmOverloads
/*     */     @NotNull
/*     */     public final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content, int offset) {
/*     */       return create$default(this, contentType, content, offset, 0, 8, (Object)null);
/*     */     }
/*     */     
/*     */     @JvmStatic
/*     */     @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType, offset, byteCount)"), level = DeprecationLevel.WARNING)
/*     */     @JvmOverloads
/*     */     @NotNull
/*     */     public final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content) {
/*     */       return create$default(this, contentType, content, 0, 0, 12, (Object)null);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/RequestBody.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */