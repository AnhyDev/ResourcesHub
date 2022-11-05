/*     */ package okhttp3;
/*     */ 
/*     */ import kotlin.jvm.JvmName;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import okio.Buffer;
/*     */ import okio.BufferedSink;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000N\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\002\n\002\020\t\n\002\b\003\n\002\020\b\n\002\b\n\n\002\030\002\n\000\n\002\020\013\n\000\n\002\020\002\n\002\b\004\030\000 #2\0020\001:\003\"#$B%\b\000\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\f\020\006\032\b\022\004\022\0020\b0\007¢\006\002\020\tJ\r\020\n\032\0020\013H\007¢\006\002\b\025J\b\020\r\032\0020\016H\026J\b\020\017\032\0020\005H\026J\016\020\026\032\0020\b2\006\020\027\032\0020\022J\023\020\006\032\b\022\004\022\0020\b0\007H\007¢\006\002\b\030J\r\020\021\032\0020\022H\007¢\006\002\b\031J\r\020\004\032\0020\005H\007¢\006\002\b\032J\032\020\033\032\0020\0162\b\020\034\032\004\030\0010\0352\006\020\036\032\0020\037H\002J\020\020 \032\0020!2\006\020\034\032\0020\035H\026R\021\020\n\032\0020\0138G¢\006\006\032\004\b\n\020\fR\016\020\002\032\0020\003X\004¢\006\002\n\000R\016\020\r\032\0020\016X\016¢\006\002\n\000R\016\020\017\032\0020\005X\004¢\006\002\n\000R\031\020\006\032\b\022\004\022\0020\b0\0078\007¢\006\b\n\000\032\004\b\006\020\020R\021\020\021\032\0020\0228G¢\006\006\032\004\b\021\020\023R\023\020\004\032\0020\0058\007¢\006\b\n\000\032\004\b\004\020\024¨\006%"}, d2 = {"Lokhttp3/MultipartBody;", "Lokhttp3/RequestBody;", "boundaryByteString", "Lokio/ByteString;", "type", "Lokhttp3/MediaType;", "parts", "", "Lokhttp3/MultipartBody$Part;", "(Lokio/ByteString;Lokhttp3/MediaType;Ljava/util/List;)V", "boundary", "", "()Ljava/lang/String;", "contentLength", "", "contentType", "()Ljava/util/List;", "size", "", "()I", "()Lokhttp3/MediaType;", "-deprecated_boundary", "part", "index", "-deprecated_parts", "-deprecated_size", "-deprecated_type", "writeOrCountBytes", "sink", "Lokio/BufferedSink;", "countBytes", "", "writeTo", "", "Builder", "Companion", "Part", "okhttp"})
/*     */ public final class MultipartBody extends RequestBody {
/*     */   private final MediaType contentType;
/*     */   private long contentLength;
/*     */   private final ByteString boundaryByteString;
/*     */   @NotNull
/*     */   private final MediaType type;
/*     */   @NotNull
/*     */   private final List<Part> parts;
/*     */   @JvmField
/*     */   @NotNull
/*     */   public static final MediaType MIXED;
/*     */   @JvmField
/*     */   @NotNull
/*     */   public static final MediaType ALTERNATIVE;
/*     */   @JvmField
/*     */   @NotNull
/*     */   public static final MediaType DIGEST;
/*     */   @JvmField
/*     */   @NotNull
/*     */   public static final MediaType PARALLEL;
/*     */   @JvmField
/*     */   @NotNull
/*     */   public static final MediaType FORM;
/*     */   private static final byte[] COLONSPACE;
/*     */   private static final byte[] CRLF;
/*     */   private static final byte[] DASHDASH;
/*     */   
/*  37 */   public MultipartBody(@NotNull ByteString boundaryByteString, @NotNull MediaType type, @NotNull List<Part> parts) { this.boundaryByteString = boundaryByteString; this.type = type; this.parts = parts;
/*  38 */     this.contentType = MediaType.Companion.get(this.type + "; boundary=" + boundary());
/*  39 */     this.contentLength = -1L; } @JvmName(name = "type") @NotNull public final MediaType type() { return this.type; }
/*     */   @JvmName(name = "parts") @NotNull public final List<Part> parts() { return this.parts; }
/*     */   @JvmName(name = "boundary") @NotNull
/*  42 */   public final String boundary() { return this.boundaryByteString.utf8(); }
/*     */ 
/*     */   
/*     */   @JvmName(name = "size")
/*  46 */   public final int size() { return this.parts.size(); } @NotNull
/*     */   public final Part part(int index) {
/*  48 */     return this.parts.get(index);
/*     */   } @NotNull
/*     */   public MediaType contentType() {
/*  51 */     return this.contentType;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "type"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_type")
/*     */   @NotNull
/*     */   public final MediaType -deprecated_type() {
/*  58 */     return this.type;
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "boundary"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_boundary")
/*     */   @NotNull
/*     */   public final String -deprecated_boundary() {
/*  65 */     return boundary();
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "size"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_size")
/*     */   public final int -deprecated_size() {
/*  72 */     return size();
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "parts"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_parts")
/*     */   @NotNull
/*     */   public final List<Part> -deprecated_parts() {
/*  79 */     return this.parts;
/*     */   }
/*     */   
/*     */   public long contentLength() throws IOException {
/*  83 */     long result = this.contentLength;
/*  84 */     if (result == -1L) {
/*  85 */       result = writeOrCountBytes(null, true);
/*  86 */       this.contentLength = result;
/*     */     } 
/*  88 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeTo(@NotNull BufferedSink sink) throws IOException {
/*  93 */     Intrinsics.checkNotNullParameter(sink, "sink"); writeOrCountBytes(sink, false);
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
/*     */   private final long writeOrCountBytes(BufferedSink sink, boolean countBytes) throws IOException
/*     */   {
/* 107 */     BufferedSink bufferedSink = sink;
/* 108 */     long byteCount = 0L;
/*     */     
/* 110 */     Buffer byteCountBuffer = (Buffer)null;
/* 111 */     if (countBytes) {
/* 112 */       byteCountBuffer = new Buffer();
/* 113 */       bufferedSink = (BufferedSink)byteCountBuffer;
/*     */     }  byte b;
/*     */     int i;
/* 116 */     for (b = 0, i = this.parts.size(); b < i; b++) {
/* 117 */       Part part = this.parts.get(b);
/* 118 */       Headers headers = part.headers();
/* 119 */       RequestBody body = part.body();
/*     */       
/* 121 */       Intrinsics.checkNotNull(bufferedSink); bufferedSink.write(DASHDASH);
/* 122 */       bufferedSink.write(this.boundaryByteString);
/* 123 */       bufferedSink.write(CRLF);
/*     */       
/* 125 */       if (headers != null) {
/* 126 */         byte b1; int j; for (b1 = 0, j = headers.size(); b1 < j; b1++) {
/* 127 */           bufferedSink.writeUtf8(headers.name(b1))
/* 128 */             .write(COLONSPACE)
/* 129 */             .writeUtf8(headers.value(b1))
/* 130 */             .write(CRLF);
/*     */         }
/*     */       } 
/*     */       
/* 134 */       MediaType contentType = body.contentType();
/* 135 */       if (contentType != null) {
/* 136 */         bufferedSink.writeUtf8("Content-Type: ")
/* 137 */           .writeUtf8(contentType.toString())
/* 138 */           .write(CRLF);
/*     */       }
/*     */       
/* 141 */       long contentLength = body.contentLength();
/* 142 */       if (contentLength != -1L) {
/* 143 */         bufferedSink.writeUtf8("Content-Length: ")
/* 144 */           .writeDecimalLong(contentLength)
/* 145 */           .write(CRLF);
/* 146 */       } else if (countBytes) {
/*     */         
/* 148 */         Intrinsics.checkNotNull(byteCountBuffer); byteCountBuffer.clear();
/* 149 */         return -1L;
/*     */       } 
/*     */       
/* 152 */       bufferedSink.write(CRLF);
/*     */       
/* 154 */       if (countBytes) {
/* 155 */         byteCount += contentLength;
/*     */       } else {
/* 157 */         body.writeTo(bufferedSink);
/*     */       } 
/*     */       
/* 160 */       bufferedSink.write(CRLF);
/*     */     } 
/*     */     
/* 163 */     Intrinsics.checkNotNull(bufferedSink); bufferedSink.write(DASHDASH);
/* 164 */     bufferedSink.write(this.boundaryByteString);
/* 165 */     bufferedSink.write(DASHDASH);
/* 166 */     bufferedSink.write(CRLF);
/*     */     
/* 168 */     if (countBytes) {
/* 169 */       Intrinsics.checkNotNull(byteCountBuffer); byteCount += byteCountBuffer.size();
/* 170 */       byteCountBuffer.clear();
/*     */     } 
/*     */     
/* 173 */     return byteCount; } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\030\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\007\030\000 \0132\0020\001:\001\013B\031\b\002\022\b\020\002\032\004\030\0010\003\022\006\020\004\032\0020\005¢\006\002\020\006J\r\020\004\032\0020\005H\007¢\006\002\b\tJ\017\020\002\032\004\030\0010\003H\007¢\006\002\b\nR\023\020\004\032\0020\0058\007¢\006\b\n\000\032\004\b\004\020\007R\025\020\002\032\004\030\0010\0038\007¢\006\b\n\000\032\004\b\002\020\b¨\006\f"}, d2 = {"Lokhttp3/MultipartBody$Part;", "", "headers", "Lokhttp3/Headers;", "body", "Lokhttp3/RequestBody;", "(Lokhttp3/Headers;Lokhttp3/RequestBody;)V", "()Lokhttp3/RequestBody;", "()Lokhttp3/Headers;", "-deprecated_body", "-deprecated_headers", "Companion", "okhttp"})
/*     */   public static final class Part { @Nullable
/*     */     private final Headers headers; @NotNull
/* 176 */     private final RequestBody body; public static final Companion Companion = new Companion(null); private Part(Headers headers, RequestBody body) { this.headers = headers; this.body = body; } @JvmName(name = "headers") @Nullable
/* 177 */     public final Headers headers() { return this.headers; } @JvmName(name = "body") @NotNull
/* 178 */     public final RequestBody body() { return this.body; }
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "headers"), level = DeprecationLevel.ERROR)
/*     */     @JvmName(name = "-deprecated_headers")
/*     */     @Nullable
/*     */     public final Headers -deprecated_headers() {
/* 186 */       return this.headers;
/*     */     }
/*     */     
/*     */     @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "body"), level = DeprecationLevel.ERROR)
/*     */     @JvmName(name = "-deprecated_body")
/*     */     @NotNull
/*     */     public final RequestBody -deprecated_body() {
/* 193 */       return this.body;
/*     */     } @JvmStatic @NotNull public static final Part create(@NotNull RequestBody body) { return Companion.create(body); } @JvmStatic @NotNull public static final Part create(@Nullable Headers headers, @NotNull RequestBody body) { return Companion.create(headers, body); } @JvmStatic @NotNull public static final Part createFormData(@NotNull String name, @NotNull String value) { return Companion.createFormData(name, value); }
/*     */     @JvmStatic @NotNull public static final Part createFormData(@NotNull String name, @Nullable String filename, @NotNull RequestBody body) { return Companion.createFormData(name, filename, body); }
/*     */     @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000(\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\003\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\032\020\003\032\0020\0042\b\020\005\032\004\030\0010\0062\006\020\007\032\0020\bH\007J\020\020\003\032\0020\0042\006\020\007\032\0020\bH\007J\030\020\t\032\0020\0042\006\020\n\032\0020\0132\006\020\f\032\0020\013H\007J\"\020\t\032\0020\0042\006\020\n\032\0020\0132\b\020\r\032\004\030\0010\0132\006\020\007\032\0020\bH\007¨\006\016"}, d2 = {"Lokhttp3/MultipartBody$Part$Companion;", "", "()V", "create", "Lokhttp3/MultipartBody$Part;", "headers", "Lokhttp3/Headers;", "body", "Lokhttp3/RequestBody;", "createFormData", "name", "", "value", "filename", "okhttp"}) public static final class Companion { private Companion() {}
/* 197 */       @JvmStatic @NotNull public final MultipartBody.Part create(@NotNull RequestBody body) { Intrinsics.checkNotNullParameter(body, "body"); return create(null, body); }
/*     */       @JvmStatic @NotNull public final MultipartBody.Part createFormData(@NotNull String name, @NotNull String value) { Intrinsics.checkNotNullParameter(name, "name");
/*     */         Intrinsics.checkNotNullParameter(value, "value");
/*     */         return createFormData(name, null, RequestBody.Companion.create$default(RequestBody.Companion, value, (MediaType)null, 1, (Object)null)); } @JvmStatic @NotNull
/* 201 */       public final MultipartBody.Part create(@Nullable Headers headers, @NotNull RequestBody body) { Intrinsics.checkNotNullParameter(body, "body"); boolean bool1 = (((headers != null) ? headers.get("Content-Type") : null) == null) ? true : false, bool2 = false, bool3 = false; if (!bool1)
/*     */         
/*     */         { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 346 */           int $i$a$-require-MultipartBody$Part$Companion$create$1 = 0; String str = "Unexpected header: Content-Type"; throw (Throwable)new IllegalArgumentException(str.toString()); }  bool1 = (((headers != null) ? headers.get("Content-Length") : null) == null) ? true : false; bool2 = false; bool3 = false; if (!bool1) { int $i$a$-require-MultipartBody$Part$Companion$create$2 = 0; String str = "Unexpected header: Content-Length"; throw (Throwable)new IllegalArgumentException(str.toString()); }  return new MultipartBody.Part(headers, body, null); } @JvmStatic @NotNull public final MultipartBody.Part createFormData(@NotNull String name, @Nullable String filename, @NotNull RequestBody body) { Intrinsics.checkNotNullParameter(name, "name"); Intrinsics.checkNotNullParameter(body, "body"); boolean bool1 = false, bool2 = false; StringBuilder stringBuilder1 = new StringBuilder(); boolean bool3 = false, bool4 = false; StringBuilder $this$buildString = stringBuilder1; int $i$a$-buildString-MultipartBody$Part$Companion$createFormData$disposition$1 = 0; $this$buildString.append("form-data; name="); MultipartBody.Companion.appendQuotedString$okhttp($this$buildString, name); if (filename != null) { $this$buildString.append("; filename="); MultipartBody.Companion.appendQuotedString$okhttp($this$buildString, filename); }  Intrinsics.checkNotNullExpressionValue(stringBuilder1.toString(), "StringBuilder().apply(builderAction).toString()"); String disposition = stringBuilder1.toString(); Headers headers = (new Headers.Builder()).addUnsafeNonAscii("Content-Disposition", disposition).build(); return create(headers, body); } } } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000@\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\000\n\002\030\002\n\000\n\002\020!\n\002\030\002\n\000\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\030\0002\0020\001B\021\b\007\022\b\b\002\020\002\032\0020\003¢\006\002\020\004J\026\020\013\032\0020\0002\006\020\f\032\0020\0032\006\020\r\032\0020\003J \020\013\032\0020\0002\006\020\f\032\0020\0032\b\020\016\032\004\030\0010\0032\006\020\017\032\0020\020J\030\020\021\032\0020\0002\b\020\022\032\004\030\0010\0232\006\020\017\032\0020\020J\016\020\021\032\0020\0002\006\020\024\032\0020\bJ\016\020\021\032\0020\0002\006\020\017\032\0020\020J\006\020\025\032\0020\026J\016\020\027\032\0020\0002\006\020\t\032\0020\nR\016\020\002\032\0020\005X\004¢\006\002\n\000R\024\020\006\032\b\022\004\022\0020\b0\007X\004¢\006\002\n\000R\016\020\t\032\0020\nX\016¢\006\002\n\000¨\006\030"}, d2 = {"Lokhttp3/MultipartBody$Builder;", "", "boundary", "", "(Ljava/lang/String;)V", "Lokio/ByteString;", "parts", "", "Lokhttp3/MultipartBody$Part;", "type", "Lokhttp3/MediaType;", "addFormDataPart", "name", "value", "filename", "body", "Lokhttp3/RequestBody;", "addPart", "headers", "Lokhttp3/Headers;", "part", "build", "Lokhttp3/MultipartBody;", "setType", "okhttp"}) public static final class Builder { private final ByteString boundary; private MediaType type; private final List<MultipartBody.Part> parts; @JvmOverloads public Builder(@NotNull String boundary) { this.boundary = ByteString.Companion.encodeUtf8(boundary); this.type = MultipartBody.MIXED; boolean bool = false; this.parts = new ArrayList<>(); } @NotNull public final Builder setType(@NotNull MediaType type) { Intrinsics.checkNotNullParameter(type, "type"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-MultipartBody$Builder$setType$1 = 0; boolean bool = Intrinsics.areEqual(type.type(), "multipart"); boolean bool3 = false, bool4 = false; if (!bool) { int $i$a$-require-MultipartBody$Builder$setType$1$1 = 0; String str = "multipart != " + type; throw (Throwable)new IllegalArgumentException(str.toString()); }  $this$apply.type = type; return builder1; } @NotNull public final Builder addPart(@NotNull RequestBody body) { Intrinsics.checkNotNullParameter(body, "body"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-MultipartBody$Builder$addPart$1 = 0; $this$apply.addPart(MultipartBody.Part.Companion.create(body)); return builder1; } @NotNull public final Builder addPart(@Nullable Headers headers, @NotNull RequestBody body) { Intrinsics.checkNotNullParameter(body, "body"); Builder builder1 = this; boolean bool1 = false, bool2 = false; Builder $this$apply = builder1; int $i$a$-apply-MultipartBody$Builder$addPart$2 = 0; $this$apply.addPart(MultipartBody.Part.Companion.create(headers, body)); return builder1; } @NotNull public final MultipartBody build() { List<MultipartBody.Part> list = this.parts; boolean bool2 = false, bool1 = !list.isEmpty() ? true : false; bool2 = false; boolean bool3 = false; if (!bool1) { int $i$a$-check-MultipartBody$Builder$build$1 = 0;
/*     */         String str = "Multipart body must have at least one part.";
/*     */         throw (Throwable)new IllegalStateException(str.toString()); }
/*     */       
/*     */       return new MultipartBody(this.boundary, this.type, Util.toImmutableList(this.parts)); }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public final Builder addFormDataPart(@NotNull String name, @NotNull String value) {
/*     */       Intrinsics.checkNotNullParameter(name, "name");
/*     */       Intrinsics.checkNotNullParameter(value, "value");
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-MultipartBody$Builder$addFormDataPart$1 = 0;
/*     */       $this$apply.addPart(MultipartBody.Part.Companion.createFormData(name, value));
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder addFormDataPart(@NotNull String name, @Nullable String filename, @NotNull RequestBody body) {
/*     */       Intrinsics.checkNotNullParameter(name, "name");
/*     */       Intrinsics.checkNotNullParameter(body, "body");
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-MultipartBody$Builder$addFormDataPart$2 = 0;
/*     */       $this$apply.addPart(MultipartBody.Part.Companion.createFormData(name, filename, body));
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Builder addPart(@NotNull MultipartBody.Part part) {
/*     */       Intrinsics.checkNotNullParameter(part, "part");
/*     */       Builder builder1 = this;
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Builder $this$apply = builder1;
/*     */       int $i$a$-apply-MultipartBody$Builder$addPart$3 = 0;
/*     */       List<MultipartBody.Part> list = $this$apply.parts;
/*     */       MultipartBody.Part part1 = part;
/*     */       boolean bool3 = false;
/*     */       list.add(part1);
/*     */       return builder1;
/*     */     }
/*     */     
/*     */     @JvmOverloads
/*     */     public Builder() {
/*     */       this(null, 1, null);
/*     */     } }
/*     */   
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   static {
/*     */     MIXED = MediaType.Companion.get("multipart/mixed");
/*     */     ALTERNATIVE = MediaType.Companion.get("multipart/alternative");
/*     */     DIGEST = MediaType.Companion.get("multipart/digest");
/*     */     PARALLEL = MediaType.Companion.get("multipart/parallel");
/*     */     FORM = MediaType.Companion.get("multipart/form-data");
/*     */     COLONSPACE = new byte[] { (byte)58, (byte)32 };
/*     */     CRLF = new byte[] { (byte)13, (byte)10 };
/*     */     DASHDASH = new byte[] { (byte)45, (byte)45 };
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0000\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\022\n\002\b\007\n\002\020\002\n\002\030\002\n\002\030\002\n\000\n\002\020\016\n\002\b\002\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\035\020\r\032\0020\016*\0060\017j\002`\0202\006\020\021\032\0020\022H\000¢\006\002\b\023R\020\020\003\032\0020\0048\006X\004¢\006\002\n\000R\016\020\005\032\0020\006X\004¢\006\002\n\000R\016\020\007\032\0020\006X\004¢\006\002\n\000R\016\020\b\032\0020\006X\004¢\006\002\n\000R\020\020\t\032\0020\0048\006X\004¢\006\002\n\000R\020\020\n\032\0020\0048\006X\004¢\006\002\n\000R\020\020\013\032\0020\0048\006X\004¢\006\002\n\000R\020\020\f\032\0020\0048\006X\004¢\006\002\n\000¨\006\024"}, d2 = {"Lokhttp3/MultipartBody$Companion;", "", "()V", "ALTERNATIVE", "Lokhttp3/MediaType;", "COLONSPACE", "", "CRLF", "DASHDASH", "DIGEST", "FORM", "MIXED", "PARALLEL", "appendQuotedString", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "key", "", "appendQuotedString$okhttp", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */     
/*     */     public final void appendQuotedString$okhttp(@NotNull StringBuilder $this$appendQuotedString, @NotNull String key) {
/*     */       Intrinsics.checkNotNullParameter($this$appendQuotedString, "$this$appendQuotedString");
/*     */       Intrinsics.checkNotNullParameter(key, "key");
/*     */       $this$appendQuotedString.append('"');
/*     */       byte b;
/*     */       int i;
/*     */       for (b = 0, i = key.length(); b < i; b++) {
/*     */         char c = key.charAt(b);
/*     */         switch (c) {
/*     */           case '\n':
/*     */             $this$appendQuotedString.append("%0A");
/*     */             break;
/*     */           case '\r':
/*     */             $this$appendQuotedString.append("%0D");
/*     */             break;
/*     */           case '"':
/*     */             $this$appendQuotedString.append("%22");
/*     */             break;
/*     */           default:
/*     */             $this$appendQuotedString.append(c);
/*     */             break;
/*     */         } 
/*     */       } 
/*     */       $this$appendQuotedString.append('"');
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/MultipartBody.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */