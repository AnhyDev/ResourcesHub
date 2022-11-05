/*     */ package okhttp3;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.Flushable;
/*     */ import java.io.IOException;
/*     */ import java.security.cert.Certificate;
/*     */ import java.security.cert.CertificateEncodingException;
/*     */ import java.security.cert.CertificateException;
/*     */ import java.security.cert.CertificateFactory;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import kotlin.Deprecated;
/*     */ import kotlin.DeprecationLevel;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.ReplaceWith;
/*     */ import kotlin.Unit;
/*     */ import kotlin.collections.CollectionsKt;
/*     */ import kotlin.collections.SetsKt;
/*     */ import kotlin.io.CloseableKt;
/*     */ import kotlin.jvm.JvmName;
/*     */ import kotlin.jvm.JvmStatic;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.jvm.internal.StringCompanionObject;
/*     */ import kotlin.jvm.internal.markers.KMutableIterator;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.internal.Util;
/*     */ import okhttp3.internal.cache.CacheRequest;
/*     */ import okhttp3.internal.cache.CacheStrategy;
/*     */ import okhttp3.internal.cache.DiskLruCache;
/*     */ import okhttp3.internal.concurrent.TaskRunner;
/*     */ import okhttp3.internal.http.HttpMethod;
/*     */ import okhttp3.internal.http.StatusLine;
/*     */ import okhttp3.internal.io.FileSystem;
/*     */ import okhttp3.internal.platform.Platform;
/*     */ import okio.Buffer;
/*     */ import okio.BufferedSink;
/*     */ import okio.BufferedSource;
/*     */ import okio.ByteString;
/*     */ import okio.ForwardingSink;
/*     */ import okio.ForwardingSource;
/*     */ import okio.Okio;
/*     */ import okio.Sink;
/*     */ import okio.Source;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000r\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020\b\n\000\n\002\020\013\n\002\b\f\n\002\020\002\n\000\n\002\030\002\n\002\b\006\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\t\n\002\030\002\n\002\b\006\n\002\020)\n\002\020\016\n\002\b\005\030\000 C2\0020\0012\0020\002:\004BCDEB\027\b\026\022\006\020\003\032\0020\004\022\006\020\005\032\0020\006¢\006\002\020\007B\037\b\000\022\006\020\003\032\0020\004\022\006\020\005\032\0020\006\022\006\020\b\032\0020\t¢\006\002\020\nJ\026\020\037\032\0020 2\f\020!\032\b\030\0010\"R\0020\fH\002J\b\020#\032\0020 H\026J\006\020$\032\0020 J\r\020\003\032\0020\004H\007¢\006\002\b%J\006\020&\032\0020 J\b\020'\032\0020 H\026J\027\020(\032\004\030\0010)2\006\020*\032\0020+H\000¢\006\002\b,J\006\020\020\032\0020\021J\006\020-\032\0020 J\006\020\005\032\0020\006J\006\020\025\032\0020\021J\027\020.\032\004\030\0010/2\006\0200\032\0020)H\000¢\006\002\b1J\025\0202\032\0020 2\006\020*\032\0020+H\000¢\006\002\b3J\006\020\026\032\0020\021J\006\0204\032\0020\006J\r\0205\032\0020 H\000¢\006\002\b6J\025\0207\032\0020 2\006\0208\032\00209H\000¢\006\002\b:J\035\020;\032\0020 2\006\020<\032\0020)2\006\020=\032\0020)H\000¢\006\002\b>J\f\020?\032\b\022\004\022\0020A0@J\006\020\027\032\0020\021J\006\020\034\032\0020\021R\024\020\013\032\0020\fX\004¢\006\b\n\000\032\004\b\r\020\016R\021\020\003\032\0020\0048G¢\006\006\032\004\b\003\020\017R\016\020\020\032\0020\021X\016¢\006\002\n\000R\021\020\022\032\0020\0238F¢\006\006\032\004\b\022\020\024R\016\020\025\032\0020\021X\016¢\006\002\n\000R\016\020\026\032\0020\021X\016¢\006\002\n\000R\032\020\027\032\0020\021X\016¢\006\016\n\000\032\004\b\030\020\031\"\004\b\032\020\033R\032\020\034\032\0020\021X\016¢\006\016\n\000\032\004\b\035\020\031\"\004\b\036\020\033¨\006F"}, d2 = {"Lokhttp3/Cache;", "Ljava/io/Closeable;", "Ljava/io/Flushable;", "directory", "Ljava/io/File;", "maxSize", "", "(Ljava/io/File;J)V", "fileSystem", "Lokhttp3/internal/io/FileSystem;", "(Ljava/io/File;JLokhttp3/internal/io/FileSystem;)V", "cache", "Lokhttp3/internal/cache/DiskLruCache;", "getCache$okhttp", "()Lokhttp3/internal/cache/DiskLruCache;", "()Ljava/io/File;", "hitCount", "", "isClosed", "", "()Z", "networkCount", "requestCount", "writeAbortCount", "getWriteAbortCount$okhttp", "()I", "setWriteAbortCount$okhttp", "(I)V", "writeSuccessCount", "getWriteSuccessCount$okhttp", "setWriteSuccessCount$okhttp", "abortQuietly", "", "editor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "close", "delete", "-deprecated_directory", "evictAll", "flush", "get", "Lokhttp3/Response;", "request", "Lokhttp3/Request;", "get$okhttp", "initialize", "put", "Lokhttp3/internal/cache/CacheRequest;", "response", "put$okhttp", "remove", "remove$okhttp", "size", "trackConditionalCacheHit", "trackConditionalCacheHit$okhttp", "trackResponse", "cacheStrategy", "Lokhttp3/internal/cache/CacheStrategy;", "trackResponse$okhttp", "update", "cached", "network", "update$okhttp", "urls", "", "", "CacheResponseBody", "Companion", "Entry", "RealCacheRequest", "okhttp"})
/*     */ public final class Cache
/*     */   implements Closeable, Flushable
/*     */ {
/*     */   @NotNull
/*     */   private final DiskLruCache cache;
/*     */   private int writeSuccessCount;
/*     */   private int writeAbortCount;
/*     */   private int networkCount;
/*     */   private int hitCount;
/*     */   private int requestCount;
/*     */   private static final int VERSION = 201105;
/*     */   private static final int ENTRY_METADATA = 0;
/*     */   private static final int ENTRY_BODY = 1;
/*     */   private static final int ENTRY_COUNT = 2;
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   public Cache(@NotNull File directory, long maxSize, @NotNull FileSystem fileSystem) {
/* 146 */     this.cache = new DiskLruCache(
/* 147 */         fileSystem, 
/* 148 */         directory, 
/* 149 */         201105, 
/* 150 */         2, 
/* 151 */         maxSize, 
/* 152 */         TaskRunner.INSTANCE);
/*     */   } @NotNull
/*     */   public final DiskLruCache getCache$okhttp() {
/*     */     return this.cache;
/* 156 */   } public final int getWriteSuccessCount$okhttp() { return this.writeSuccessCount; } public final void setWriteSuccessCount$okhttp(int <set-?>) { this.writeSuccessCount = <set-?>; }
/* 157 */   public final int getWriteAbortCount$okhttp() { return this.writeAbortCount; } public final void setWriteAbortCount$okhttp(int <set-?>) { this.writeAbortCount = <set-?>; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isClosed() {
/* 163 */     return this.cache.isClosed();
/*     */   }
/*     */   public Cache(@NotNull File directory, long maxSize) {
/* 166 */     this(directory, maxSize, FileSystem.SYSTEM);
/*     */   }
/*     */   @Nullable
/* 169 */   public final Response get$okhttp(@NotNull Request request) { Intrinsics.checkNotNullParameter(request, "request"); String key = Companion.key(request.url());
/*     */     try {
/* 171 */       if (this.cache.get(key) != null) { Entry entry1; DiskLruCache.Snapshot snapshot1 = this.cache.get(key);
/*     */ 
/*     */         
/*     */         DiskLruCache.Snapshot snapshot = snapshot1;
/*     */         
/*     */         try {
/* 177 */           entry1 = new Entry(snapshot.getSource(0));
/* 178 */         } catch (IOException _) {
/* 179 */           Util.closeQuietly((Closeable)snapshot);
/* 180 */           return null;
/*     */         } 
/*     */         Entry entry = entry1;
/* 183 */         Response response = entry.response(snapshot);
/* 184 */         if (!entry.matches(request, response)) {
/* 185 */           if (response.body() != null) { Util.closeQuietly(response.body()); } else { response.body(); }
/* 186 */            return null;
/*     */         } 
/*     */         
/* 189 */         return response; }
/*     */        this.cache.get(key); return null;
/*     */     } catch (IOException iOException) {
/*     */       return null;
/* 193 */     }  } @Nullable public final CacheRequest put$okhttp(@NotNull Response response) { Intrinsics.checkNotNullParameter(response, "response"); String requestMethod = response.request().method();
/*     */     
/* 195 */     if (HttpMethod.INSTANCE.invalidatesCache(response.request().method())) {
/*     */       try {
/* 197 */         remove$okhttp(response.request());
/* 198 */       } catch (IOException iOException) {}
/*     */ 
/*     */       
/* 201 */       return null;
/*     */     } 
/*     */     
/* 204 */     if ((Intrinsics.areEqual(requestMethod, "GET") ^ true) != 0)
/*     */     {
/*     */       
/* 207 */       return null;
/*     */     }
/*     */     
/* 210 */     if (Companion.hasVaryAll(response)) {
/* 211 */       return null;
/*     */     }
/*     */     
/* 214 */     Entry entry = new Entry(response);
/* 215 */     DiskLruCache.Editor editor = (DiskLruCache.Editor)null;
/*     */     try {
/* 217 */       if (DiskLruCache.edit$default(this.cache, Companion.key(response.request().url()), 0L, 2, null) != null) { editor = DiskLruCache.edit$default(this.cache, Companion.key(response.request().url()), 0L, 2, null);
/* 218 */         entry.writeTo(editor);
/* 219 */         return new RealCacheRequest(editor); }  DiskLruCache.edit$default(this.cache, Companion.key(response.request().url()), 0L, 2, null); return null;
/* 220 */     } catch (IOException _) {
/* 221 */       abortQuietly(editor);
/* 222 */       return null;
/*     */     }  }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void remove$okhttp(@NotNull Request request) throws IOException {
/* 228 */     Intrinsics.checkNotNullParameter(request, "request"); this.cache.remove(Companion.key(request.url()));
/*     */   }
/*     */   
/*     */   public final void update$okhttp(@NotNull Response cached, @NotNull Response network) {
/* 232 */     Intrinsics.checkNotNullParameter(cached, "cached"); Intrinsics.checkNotNullParameter(network, "network"); Entry entry = new Entry(network);
/* 233 */     if (cached.body() == null) throw new NullPointerException("null cannot be cast to non-null type okhttp3.Cache.CacheResponseBody");  DiskLruCache.Snapshot snapshot = ((CacheResponseBody)cached.body()).getSnapshot();
/* 234 */     DiskLruCache.Editor editor = (DiskLruCache.Editor)null;
/*     */     try {
/* 236 */       if (snapshot.edit() != null) { editor = snapshot.edit();
/* 237 */         entry.writeTo(editor);
/* 238 */         editor.commit(); return; }  snapshot.edit(); return;
/* 239 */     } catch (IOException _) {
/* 240 */       abortQuietly(editor);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private final void abortQuietly(DiskLruCache.Editor editor) {
/*     */     try {
/* 247 */       if (editor != null) { editor.abort(); } else {  } 
/* 248 */     } catch (IOException iOException) {}
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
/*     */   public final void initialize() throws IOException {
/* 265 */     this.cache.initialize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void delete() throws IOException {
/* 274 */     this.cache.delete();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void evictAll() throws IOException {
/* 283 */     this.cache.evictAll();
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
/*     */   @NotNull
/*     */   public final Iterator<String> urls() throws IOException {
/* 297 */     return new Cache$urls$1(); } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000'\n\000\n\002\020)\n\002\020\016\n\000\n\002\020\013\n\000\n\002\030\002\n\002\030\002\n\002\b\004\n\002\020\002\n\000*\001\000\b\n\030\0002\b\022\004\022\0020\0020\001J\t\020\t\032\0020\004H\002J\t\020\n\032\0020\002H\002J\b\020\013\032\0020\fH\026R\016\020\003\032\0020\004X\016¢\006\002\n\000R\030\020\005\032\f\022\b\022\0060\006R\0020\0070\001X\004¢\006\002\n\000R\020\020\b\032\004\030\0010\002X\016¢\006\002\n\000¨\006\r"}, d2 = {"okhttp3/Cache$urls$1", "", "", "canRemove", "", "delegate", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Lokhttp3/internal/cache/DiskLruCache;", "nextUrl", "hasNext", "next", "remove", "", "okhttp"})
/* 298 */   public static final class Cache$urls$1 implements Iterator<String>, KMutableIterator { private final Iterator<DiskLruCache.Snapshot> delegate = Cache.this.getCache$okhttp().snapshots();
/*     */     private String nextUrl;
/*     */     private boolean canRemove;
/*     */     
/*     */     public boolean hasNext() {
/* 303 */       if (this.nextUrl != null) return true;
/*     */       
/* 305 */       this.canRemove = false;
/* 306 */       while (this.delegate.hasNext()) {
/*     */         try {
/* 308 */           Closeable closeable = (Closeable)this.delegate.next(); boolean bool1 = false, bool2 = false; Throwable throwable = (Throwable)null; try { DiskLruCache.Snapshot snapshot = (DiskLruCache.Snapshot)closeable; int $i$a$-use-Cache$urls$1$hasNext$1 = 0;
/* 309 */             BufferedSource metadata = Okio.buffer(snapshot.getSource(0));
/* 310 */             this.nextUrl = metadata.readUtf8LineStrict();
/* 311 */             return true; } catch (Throwable throwable1) { throwable = throwable1 = null; throw throwable1; } finally { CloseableKt.closeFinally(closeable, throwable); }
/*     */         
/* 313 */         } catch (IOException iOException) {}
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 319 */       return false;
/*     */     }
/*     */     @NotNull
/*     */     public String next() {
/* 323 */       if (!hasNext()) throw new NoSuchElementException(); 
/* 324 */       Intrinsics.checkNotNull(this.nextUrl); String result = this.nextUrl;
/* 325 */       this.nextUrl = (String)null;
/* 326 */       this.canRemove = true;
/* 327 */       return result;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 331 */       boolean bool = this.canRemove; boolean bool1 = false, bool2 = false; if (!bool) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 784 */         int $i$a$-check-Cache$urls$1$remove$1 = 0;
/*     */         String str = "remove() before next()";
/*     */         throw new IllegalStateException(str.toString());
/*     */       } 
/*     */       this.delegate.remove();
/*     */     } }
/*     */ 
/*     */   
/*     */   public final synchronized int writeAbortCount() {
/*     */     return this.writeAbortCount;
/*     */   }
/*     */   
/*     */   public final synchronized int writeSuccessCount() {
/*     */     return this.writeSuccessCount;
/*     */   }
/*     */   
/*     */   public final long size() throws IOException {
/*     */     return this.cache.size();
/*     */   }
/*     */   
/*     */   public final long maxSize() {
/*     */     return this.cache.getMaxSize();
/*     */   }
/*     */   
/*     */   public void flush() throws IOException {
/*     */     this.cache.flush();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*     */     this.cache.close();
/*     */   }
/*     */   
/*     */   @JvmName(name = "directory")
/*     */   @NotNull
/*     */   public final File directory() {
/*     */     return this.cache.getDirectory();
/*     */   }
/*     */   
/*     */   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "directory"), level = DeprecationLevel.ERROR)
/*     */   @JvmName(name = "-deprecated_directory")
/*     */   @NotNull
/*     */   public final File -deprecated_directory() {
/*     */     return this.cache.getDirectory();
/*     */   }
/*     */   
/*     */   public final synchronized void trackResponse$okhttp(@NotNull CacheStrategy cacheStrategy) {
/*     */     Intrinsics.checkNotNullParameter(cacheStrategy, "cacheStrategy");
/*     */     int i;
/*     */     this.requestCount = (i = this.requestCount) + 1;
/*     */     if (cacheStrategy.getNetworkRequest() != null) {
/*     */       this.networkCount = (i = this.networkCount) + 1;
/*     */     } else if (cacheStrategy.getCacheResponse() != null) {
/*     */       this.hitCount = (i = this.hitCount) + 1;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final synchronized void trackConditionalCacheHit$okhttp() {
/*     */     int i;
/*     */     this.hitCount = (i = this.hitCount) + 1;
/*     */   }
/*     */   
/*     */   public final synchronized int networkCount() {
/*     */     return this.networkCount;
/*     */   }
/*     */   
/*     */   public final synchronized int hitCount() {
/*     */     return this.hitCount;
/*     */   }
/*     */   
/*     */   public final synchronized int requestCount() {
/*     */     return this.requestCount;
/*     */   }
/*     */   
/*     */   @JvmStatic
/*     */   @NotNull
/*     */   public static final String key(@NotNull HttpUrl url) {
/*     */     return Companion.key(url);
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000,\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\005\n\002\020\002\n\000\b\004\030\0002\0020\001B\021\022\n\020\002\032\0060\003R\0020\004¢\006\002\020\005J\b\020\017\032\0020\020H\026J\b\020\006\032\0020\007H\026R\016\020\006\032\0020\007X\004¢\006\002\n\000R\016\020\b\032\0020\007X\004¢\006\002\n\000R\032\020\t\032\0020\nX\016¢\006\016\n\000\032\004\b\013\020\f\"\004\b\r\020\016R\022\020\002\032\0060\003R\0020\004X\004¢\006\002\n\000¨\006\021"}, d2 = {"Lokhttp3/Cache$RealCacheRequest;", "Lokhttp3/internal/cache/CacheRequest;", "editor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "(Lokhttp3/Cache;Lokhttp3/internal/cache/DiskLruCache$Editor;)V", "body", "Lokio/Sink;", "cacheOut", "done", "", "getDone", "()Z", "setDone", "(Z)V", "abort", "", "okhttp"})
/*     */   private final class RealCacheRequest implements CacheRequest {
/*     */     private final Sink cacheOut;
/*     */     private final Sink body;
/*     */     private boolean done;
/*     */     private final DiskLruCache.Editor editor;
/*     */     
/*     */     public RealCacheRequest(DiskLruCache.Editor editor) {
/*     */       this.editor = editor;
/*     */       this.cacheOut = this.editor.newSink(1);
/*     */       this.body = (Sink)new ForwardingSink(this.cacheOut) {
/*     */           public void close() throws IOException {
/*     */             Cache cache = Cache.this;
/*     */             boolean bool = false;
/*     */             synchronized (false) {
/*     */               int $i$a$-synchronized-Cache$RealCacheRequest$1$close$1 = 0;
/*     */               if (Cache.RealCacheRequest.this.getDone()) {
/*     */                 /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/Cache}, name=null} */
/*     */                 return;
/*     */               } 
/*     */               Cache.RealCacheRequest.this.setDone(true);
/*     */               int i;
/*     */               Cache.this.setWriteSuccessCount$okhttp((i = Cache.this.getWriteSuccessCount$okhttp()) + 1);
/*     */               null = i;
/*     */               /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/Cache}, name=null} */
/*     */             } 
/*     */             super.close();
/*     */             Cache.RealCacheRequest.this.editor.commit();
/*     */           }
/*     */         };
/*     */     }
/*     */     
/*     */     public final boolean getDone() {
/*     */       return this.done;
/*     */     }
/*     */     
/*     */     public final void setDone(boolean <set-?>) {
/*     */       this.done = <set-?>;
/*     */     }
/*     */     
/*     */     public void abort() {
/*     */       Cache cache = Cache.this;
/*     */       boolean bool = false;
/*     */       synchronized (false) {
/*     */         int $i$a$-synchronized-Cache$RealCacheRequest$abort$1 = 0;
/*     */         if (this.done) {
/*     */           /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/Cache}, name=null} */
/*     */           return;
/*     */         } 
/*     */         this.done = true;
/*     */         int i;
/*     */         Cache.this.setWriteAbortCount$okhttp((i = Cache.this.getWriteAbortCount$okhttp()) + 1);
/*     */         null = i;
/*     */         /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{okhttp3/Cache}, name=null} */
/*     */       } 
/*     */       Util.closeQuietly((Closeable)this.cacheOut);
/*     */       try {
/*     */         this.editor.abort();
/*     */       } catch (IOException iOException) {}
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public Sink body() {
/*     */       return this.body;
/*     */     }
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\001\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\020\016\n\000\n\002\030\002\n\000\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\000\n\002\020 \n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\b\002\030\000 .2\0020\001:\001.B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B\017\b\026\022\006\020\005\032\0020\006¢\006\002\020\007J\026\020\033\032\0020\r2\006\020\034\032\0020\0352\006\020\005\032\0020\006J\026\020\036\032\b\022\004\022\0020 0\0372\006\020!\032\0020\"H\002J\022\020\005\032\0020\0062\n\020#\032\0060$R\0020%J\036\020&\032\0020'2\006\020(\032\0020)2\f\020*\032\b\022\004\022\0020 0\037H\002J\022\020+\032\0020'2\n\020,\032\0060-R\0020%R\016\020\b\032\0020\tX\004¢\006\002\n\000R\020\020\n\032\004\030\0010\013X\004¢\006\002\n\000R\024\020\f\032\0020\r8BX\004¢\006\006\032\004\b\f\020\016R\016\020\017\032\0020\020X\004¢\006\002\n\000R\016\020\021\032\0020\022X\004¢\006\002\n\000R\016\020\023\032\0020\024X\004¢\006\002\n\000R\016\020\025\032\0020\020X\004¢\006\002\n\000R\016\020\026\032\0020\027X\004¢\006\002\n\000R\016\020\030\032\0020\024X\004¢\006\002\n\000R\016\020\031\032\0020\020X\004¢\006\002\n\000R\016\020\032\032\0020\027X\004¢\006\002\n\000¨\006/"}, d2 = {"Lokhttp3/Cache$Entry;", "", "rawSource", "Lokio/Source;", "(Lokio/Source;)V", "response", "Lokhttp3/Response;", "(Lokhttp3/Response;)V", "code", "", "handshake", "Lokhttp3/Handshake;", "isHttps", "", "()Z", "message", "", "protocol", "Lokhttp3/Protocol;", "receivedResponseMillis", "", "requestMethod", "responseHeaders", "Lokhttp3/Headers;", "sentRequestMillis", "url", "varyHeaders", "matches", "request", "Lokhttp3/Request;", "readCertificateList", "", "Ljava/security/cert/Certificate;", "source", "Lokio/BufferedSource;", "snapshot", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Lokhttp3/internal/cache/DiskLruCache;", "writeCertList", "", "sink", "Lokio/BufferedSink;", "certificates", "writeTo", "editor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Companion", "okhttp"})
/*     */   private static final class Entry {
/*     */     private final String url;
/*     */     private final Headers varyHeaders;
/*     */     private final String requestMethod;
/*     */     private final Protocol protocol;
/*     */     private final int code;
/*     */     private final String message;
/*     */     private final Headers responseHeaders;
/*     */     private final Handshake handshake;
/*     */     private final long sentRequestMillis;
/*     */     private final long receivedResponseMillis;
/*     */     private static final String SENT_MILLIS = Platform.Companion.get().getPrefix() + "-Sent-Millis";
/*     */     private static final String RECEIVED_MILLIS = Platform.Companion.get().getPrefix() + "-Received-Millis";
/*     */     
/*     */     private final boolean isHttps() {
/*     */       return StringsKt.startsWith$default(this.url, "https://", false, 2, null);
/*     */     }
/*     */     
/*     */     public Entry(@NotNull Source rawSource) throws IOException {
/*     */       try {
/*     */         BufferedSource source = Okio.buffer(rawSource);
/*     */         this.url = source.readUtf8LineStrict();
/*     */         this.requestMethod = source.readUtf8LineStrict();
/*     */         Headers.Builder varyHeadersBuilder = new Headers.Builder();
/*     */         int varyRequestHeaderLineCount = Cache.Companion.readInt$okhttp(source);
/*     */         byte b1;
/*     */         int i;
/*     */         for (b1 = 0, i = varyRequestHeaderLineCount; b1 < i; b1++)
/*     */           varyHeadersBuilder.addLenient$okhttp(source.readUtf8LineStrict()); 
/*     */         this.varyHeaders = varyHeadersBuilder.build();
/*     */         StatusLine statusLine = StatusLine.Companion.parse(source.readUtf8LineStrict());
/*     */         this.protocol = statusLine.protocol;
/*     */         this.code = statusLine.code;
/*     */         this.message = statusLine.message;
/*     */         Headers.Builder responseHeadersBuilder = new Headers.Builder();
/*     */         int responseHeaderLineCount = Cache.Companion.readInt$okhttp(source);
/*     */         byte b2;
/*     */         int j;
/*     */         for (b2 = 0, j = responseHeaderLineCount; b2 < j; b2++)
/*     */           responseHeadersBuilder.addLenient$okhttp(source.readUtf8LineStrict()); 
/*     */         String sendRequestMillisString = responseHeadersBuilder.get(SENT_MILLIS);
/*     */         String receivedResponseMillisString = responseHeadersBuilder.get(RECEIVED_MILLIS);
/*     */         responseHeadersBuilder.removeAll(SENT_MILLIS);
/*     */         responseHeadersBuilder.removeAll(RECEIVED_MILLIS);
/*     */         String str1 = sendRequestMillisString;
/*     */         boolean bool = false;
/*     */         this.sentRequestMillis = (sendRequestMillisString != null) ? Long.parseLong(str1) : 0L;
/*     */         str1 = receivedResponseMillisString;
/*     */         bool = false;
/*     */         this.receivedResponseMillis = (receivedResponseMillisString != null) ? Long.parseLong(str1) : 0L;
/*     */         this.responseHeaders = responseHeadersBuilder.build();
/*     */         if (isHttps()) {
/*     */           String blank = source.readUtf8LineStrict();
/*     */           CharSequence charSequence = blank;
/*     */           boolean bool1 = false;
/*     */           if ((charSequence.length() > 0))
/*     */             throw new IOException("expected \"\" but was \"" + blank + '"'); 
/*     */           String cipherSuiteString = source.readUtf8LineStrict();
/*     */           CipherSuite cipherSuite = CipherSuite.Companion.forJavaName(cipherSuiteString);
/*     */           List<Certificate> peerCertificates = readCertificateList(source);
/*     */           List<Certificate> localCertificates = readCertificateList(source);
/*     */           TlsVersion tlsVersion = !source.exhausted() ? TlsVersion.Companion.forJavaName(source.readUtf8LineStrict()) : TlsVersion.SSL_3_0;
/*     */           this.handshake = Handshake.Companion.get(tlsVersion, cipherSuite, peerCertificates, localCertificates);
/*     */         } else {
/*     */           this.handshake = (Handshake)null;
/*     */         } 
/*     */       } finally {
/*     */         rawSource.close();
/*     */       } 
/*     */     }
/*     */     
/*     */     public Entry(@NotNull Response response) {
/*     */       this.url = response.request().url().toString();
/*     */       this.varyHeaders = Cache.Companion.varyHeaders(response);
/*     */       this.requestMethod = response.request().method();
/*     */       this.protocol = response.protocol();
/*     */       this.code = response.code();
/*     */       this.message = response.message();
/*     */       this.responseHeaders = response.headers();
/*     */       this.handshake = response.handshake();
/*     */       this.sentRequestMillis = response.sentRequestAtMillis();
/*     */       this.receivedResponseMillis = response.receivedResponseAtMillis();
/*     */     }
/*     */     
/*     */     public final void writeTo(@NotNull DiskLruCache.Editor editor) throws IOException {
/*     */       Intrinsics.checkNotNullParameter(editor, "editor");
/*     */       Closeable closeable = (Closeable)Okio.buffer(editor.newSink(0));
/*     */       boolean bool1 = false, bool2 = false;
/*     */       Throwable throwable = (Throwable)null;
/*     */       try {
/*     */         BufferedSink sink = (BufferedSink)closeable;
/*     */         int $i$a$-use-Cache$Entry$writeTo$1 = 0;
/*     */         sink.writeUtf8(this.url).writeByte(10);
/*     */         sink.writeUtf8(this.requestMethod).writeByte(10);
/*     */         sink.writeDecimalLong(this.varyHeaders.size()).writeByte(10);
/*     */         int j;
/*     */         for (byte b = 0; b < j; b++)
/*     */           sink.writeUtf8(this.varyHeaders.name(b)).writeUtf8(": ").writeUtf8(this.varyHeaders.value(b)).writeByte(10); 
/*     */         sink.writeUtf8((new StatusLine(this.protocol, this.code, this.message)).toString()).writeByte(10);
/*     */         sink.writeDecimalLong((this.responseHeaders.size() + 2)).writeByte(10);
/*     */         for (int i = 0; i < j; i++)
/*     */           sink.writeUtf8(this.responseHeaders.name(i)).writeUtf8(": ").writeUtf8(this.responseHeaders.value(i)).writeByte(10); 
/*     */         sink.writeUtf8(SENT_MILLIS).writeUtf8(": ").writeDecimalLong(this.sentRequestMillis).writeByte(10);
/*     */         sink.writeUtf8(RECEIVED_MILLIS).writeUtf8(": ").writeDecimalLong(this.receivedResponseMillis).writeByte(10);
/*     */         if (isHttps()) {
/*     */           sink.writeByte(10);
/*     */           Intrinsics.checkNotNull(this.handshake);
/*     */           sink.writeUtf8(this.handshake.cipherSuite().javaName()).writeByte(10);
/*     */           writeCertList(sink, this.handshake.peerCertificates());
/*     */           writeCertList(sink, this.handshake.localCertificates());
/*     */           sink.writeUtf8(this.handshake.tlsVersion().javaName()).writeByte(10);
/*     */         } 
/*     */         Unit unit = Unit.INSTANCE;
/*     */       } catch (Throwable throwable1) {
/*     */         throwable = throwable1 = null;
/*     */         throw throwable1;
/*     */       } finally {
/*     */         CloseableKt.closeFinally(closeable, throwable);
/*     */       } 
/*     */     }
/*     */     
/*     */     private final List<Certificate> readCertificateList(BufferedSource source) throws IOException {
/*     */       int length = Cache.Companion.readInt$okhttp(source);
/*     */       if (length == -1)
/*     */         return CollectionsKt.emptyList(); 
/*     */       try {
/*     */         CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
/*     */         ArrayList<Certificate> result = new ArrayList(length);
/*     */         byte b;
/*     */         int i;
/*     */         for (b = 0, i = length; b < i; b++) {
/*     */           String line = source.readUtf8LineStrict();
/*     */           Buffer bytes = new Buffer();
/*     */           Intrinsics.checkNotNull(ByteString.Companion.decodeBase64(line));
/*     */           bytes.write(ByteString.Companion.decodeBase64(line));
/*     */           result.add(certificateFactory.generateCertificate(bytes.inputStream()));
/*     */         } 
/*     */         return result;
/*     */       } catch (CertificateException e) {
/*     */         throw new IOException(e.getMessage());
/*     */       } 
/*     */     }
/*     */     
/*     */     private final void writeCertList(BufferedSink sink, List<Certificate> certificates) throws IOException {
/*     */       try {
/*     */         sink.writeDecimalLong(certificates.size()).writeByte(10);
/*     */         byte b;
/*     */         int i;
/*     */         for (b = 0, i = certificates.size(); b < i; b++) {
/*     */           byte[] bytes = ((Certificate)certificates.get(b)).getEncoded();
/*     */           Intrinsics.checkNotNullExpressionValue(bytes, "bytes");
/*     */           String line = ByteString.Companion.of$default(ByteString.Companion, bytes, 0, 0, 3, null).base64();
/*     */           sink.writeUtf8(line).writeByte(10);
/*     */         } 
/*     */       } catch (CertificateEncodingException e) {
/*     */         CertificateEncodingException certificateEncodingException1;
/*     */         throw new IOException(certificateEncodingException1.getMessage());
/*     */       } 
/*     */     }
/*     */     
/*     */     public final boolean matches(@NotNull Request request, @NotNull Response response) {
/*     */       Intrinsics.checkNotNullParameter(request, "request");
/*     */       Intrinsics.checkNotNullParameter(response, "response");
/*     */       return (Intrinsics.areEqual(this.url, request.url().toString()) && Intrinsics.areEqual(this.requestMethod, request.method()) && Cache.Companion.varyMatches(response, this.varyHeaders, request));
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Response response(@NotNull DiskLruCache.Snapshot snapshot) {
/*     */       Intrinsics.checkNotNullParameter(snapshot, "snapshot");
/*     */       String contentType = this.responseHeaders.get("Content-Type");
/*     */       String contentLength = this.responseHeaders.get("Content-Length");
/*     */       Request cacheRequest = (new Request.Builder()).url(this.url).method(this.requestMethod, null).headers(this.varyHeaders).build();
/*     */       return (new Response.Builder()).request(cacheRequest).protocol(this.protocol).code(this.code).message(this.message).headers(this.responseHeaders).body(new Cache.CacheResponseBody(snapshot, contentType, contentLength)).handshake(this.handshake).sentRequestAtMillis(this.sentRequestMillis).receivedResponseAtMillis(this.receivedResponseMillis).build();
/*     */     }
/*     */     
/*     */     @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\002\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\016\020\003\032\0020\004X\004¢\006\002\n\000R\016\020\005\032\0020\004X\004¢\006\002\n\000¨\006\006"}, d2 = {"Lokhttp3/Cache$Entry$Companion;", "", "()V", "RECEIVED_MILLIS", "", "SENT_MILLIS", "okhttp"})
/*     */     public static final class Companion {
/*     */       private Companion() {}
/*     */     }
/*     */     
/*     */     public static final Companion Companion = new Companion(null);
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\000\n\002\020\016\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\t\n\002\030\002\n\002\b\002\b\002\030\0002\0020\001B%\022\n\020\002\032\0060\003R\0020\004\022\b\020\005\032\004\030\0010\006\022\b\020\007\032\004\030\0010\006¢\006\002\020\bJ\b\020\007\032\0020\rH\026J\n\020\005\032\004\030\0010\016H\026J\b\020\017\032\0020\nH\026R\016\020\t\032\0020\nX\004¢\006\002\n\000R\020\020\007\032\004\030\0010\006X\004¢\006\002\n\000R\020\020\005\032\004\030\0010\006X\004¢\006\002\n\000R\025\020\002\032\0060\003R\0020\004¢\006\b\n\000\032\004\b\013\020\f¨\006\020"}, d2 = {"Lokhttp3/Cache$CacheResponseBody;", "Lokhttp3/ResponseBody;", "snapshot", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Lokhttp3/internal/cache/DiskLruCache;", "contentType", "", "contentLength", "(Lokhttp3/internal/cache/DiskLruCache$Snapshot;Ljava/lang/String;Ljava/lang/String;)V", "bodySource", "Lokio/BufferedSource;", "getSnapshot", "()Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "", "Lokhttp3/MediaType;", "source", "okhttp"})
/*     */   private static final class CacheResponseBody extends ResponseBody {
/*     */     private final BufferedSource bodySource;
/*     */     @NotNull
/*     */     private final DiskLruCache.Snapshot snapshot;
/*     */     private final String contentType;
/*     */     private final String contentLength;
/*     */     
/*     */     public CacheResponseBody(@NotNull DiskLruCache.Snapshot snapshot, @Nullable String contentType, @Nullable String contentLength) {
/*     */       this.snapshot = snapshot;
/*     */       this.contentType = contentType;
/*     */       this.contentLength = contentLength;
/*     */       Source source = this.snapshot.getSource(1);
/*     */       this.bodySource = Okio.buffer((Source)new ForwardingSource(source, source) {
/*     */             public void close() throws IOException {
/*     */               Cache.CacheResponseBody.this.getSnapshot().close();
/*     */               super.close();
/*     */             }
/*     */           });
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final DiskLruCache.Snapshot getSnapshot() {
/*     */       return this.snapshot;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public MediaType contentType() {
/*     */       return (this.contentType != null) ? MediaType.Companion.parse(this.contentType) : null;
/*     */     }
/*     */     
/*     */     public long contentLength() {
/*     */       return (this.contentLength != null) ? Util.toLongOrDefault(this.contentLength, -1L) : -1L;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public BufferedSource source() {
/*     */       return this.bodySource;
/*     */     }
/*     */   }
/*     */   
/*     */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000N\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\004\n\002\020\016\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\013\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\"\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\020\020\b\032\0020\t2\006\020\n\032\0020\013H\007J\025\020\f\032\0020\0042\006\020\r\032\0020\016H\000¢\006\002\b\017J\030\020\020\032\0020\0212\006\020\022\032\0020\0212\006\020\023\032\0020\021H\002J\036\020\024\032\0020\0252\006\020\026\032\0020\0272\006\020\030\032\0020\0212\006\020\031\032\0020\032J\n\020\033\032\0020\025*\0020\027J\022\020\034\032\b\022\004\022\0020\t0\035*\0020\021H\002J\n\020\020\032\0020\021*\0020\027R\016\020\003\032\0020\004XT¢\006\002\n\000R\016\020\005\032\0020\004XT¢\006\002\n\000R\016\020\006\032\0020\004XT¢\006\002\n\000R\016\020\007\032\0020\004XT¢\006\002\n\000¨\006\036"}, d2 = {"Lokhttp3/Cache$Companion;", "", "()V", "ENTRY_BODY", "", "ENTRY_COUNT", "ENTRY_METADATA", "VERSION", "key", "", "url", "Lokhttp3/HttpUrl;", "readInt", "source", "Lokio/BufferedSource;", "readInt$okhttp", "varyHeaders", "Lokhttp3/Headers;", "requestHeaders", "responseHeaders", "varyMatches", "", "cachedResponse", "Lokhttp3/Response;", "cachedRequest", "newRequest", "Lokhttp3/Request;", "hasVaryAll", "varyFields", "", "okhttp"})
/*     */   public static final class Companion {
/*     */     private Companion() {}
/*     */     
/*     */     @JvmStatic
/*     */     @NotNull
/*     */     public final String key(@NotNull HttpUrl url) {
/*     */       Intrinsics.checkNotNullParameter(url, "url");
/*     */       return ByteString.Companion.encodeUtf8(url.toString()).md5().hex();
/*     */     }
/*     */     
/*     */     public final int readInt$okhttp(@NotNull BufferedSource source) throws IOException {
/*     */       Intrinsics.checkNotNullParameter(source, "source");
/*     */       try {
/*     */         long result = source.readDecimalLong();
/*     */         String line = source.readUtf8LineStrict();
/*     */         if (result >= 0L && result <= 2147483647L) {
/*     */           CharSequence charSequence = line;
/*     */           boolean bool = false;
/*     */           if (!((charSequence.length() > 0) ? 1 : 0))
/*     */             return (int)result; 
/*     */         } 
/*     */         throw new IOException("expected an int but was \"" + result + line + '"');
/*     */       } catch (NumberFormatException e) {
/*     */         throw new IOException(e.getMessage());
/*     */       } 
/*     */     }
/*     */     
/*     */     public final boolean varyMatches(@NotNull Response cachedResponse, @NotNull Headers cachedRequest, @NotNull Request newRequest) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: ldc 'cachedResponse'
/*     */       //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */       //   6: aload_2
/*     */       //   7: ldc 'cachedRequest'
/*     */       //   9: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */       //   12: aload_3
/*     */       //   13: ldc 'newRequest'
/*     */       //   15: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
/*     */       //   18: aload_0
/*     */       //   19: checkcast okhttp3/Cache$Companion
/*     */       //   22: aload_1
/*     */       //   23: invokevirtual headers : ()Lokhttp3/Headers;
/*     */       //   26: invokespecial varyFields : (Lokhttp3/Headers;)Ljava/util/Set;
/*     */       //   29: checkcast java/lang/Iterable
/*     */       //   32: astore #4
/*     */       //   34: iconst_0
/*     */       //   35: istore #5
/*     */       //   37: aload #4
/*     */       //   39: instanceof java/util/Collection
/*     */       //   42: ifeq -> 62
/*     */       //   45: aload #4
/*     */       //   47: checkcast java/util/Collection
/*     */       //   50: invokeinterface isEmpty : ()Z
/*     */       //   55: ifeq -> 62
/*     */       //   58: iconst_1
/*     */       //   59: goto -> 125
/*     */       //   62: aload #4
/*     */       //   64: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */       //   69: astore #6
/*     */       //   71: aload #6
/*     */       //   73: invokeinterface hasNext : ()Z
/*     */       //   78: ifeq -> 124
/*     */       //   81: aload #6
/*     */       //   83: invokeinterface next : ()Ljava/lang/Object;
/*     */       //   88: astore #7
/*     */       //   90: aload #7
/*     */       //   92: checkcast java/lang/String
/*     */       //   95: astore #8
/*     */       //   97: iconst_0
/*     */       //   98: istore #9
/*     */       //   100: aload_2
/*     */       //   101: aload #8
/*     */       //   103: invokevirtual values : (Ljava/lang/String;)Ljava/util/List;
/*     */       //   106: aload_3
/*     */       //   107: aload #8
/*     */       //   109: invokevirtual headers : (Ljava/lang/String;)Ljava/util/List;
/*     */       //   112: invokestatic areEqual : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */       //   115: iconst_1
/*     */       //   116: ixor
/*     */       //   117: ifeq -> 71
/*     */       //   120: iconst_0
/*     */       //   121: goto -> 125
/*     */       //   124: iconst_1
/*     */       //   125: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #724	-> 18
/*     */       //   #784	-> 37
/*     */       //   #785	-> 62
/*     */       //   #785	-> 71
/*     */       //   #725	-> 100
/*     */       //   #786	-> 124
/*     */       //   #724	-> 125
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   97	20	8	it	Ljava/lang/String;
/*     */       //   100	17	9	$i$a$-none-Cache$Companion$varyMatches$1	I
/*     */       //   90	34	7	element$iv	Ljava/lang/Object;
/*     */       //   34	91	4	$this$none$iv	Ljava/lang/Iterable;
/*     */       //   37	88	5	$i$f$none	I
/*     */       //   0	126	0	this	Lokhttp3/Cache$Companion;
/*     */       //   0	126	1	cachedResponse	Lokhttp3/Response;
/*     */       //   0	126	2	cachedRequest	Lokhttp3/Headers;
/*     */       //   0	126	3	newRequest	Lokhttp3/Request;
/*     */     }
/*     */     
/*     */     public final boolean hasVaryAll(@NotNull Response $this$hasVaryAll) {
/*     */       Intrinsics.checkNotNullParameter($this$hasVaryAll, "$this$hasVaryAll");
/*     */       return varyFields($this$hasVaryAll.headers()).contains("*");
/*     */     }
/*     */     
/*     */     private final Set<String> varyFields(Headers $this$varyFields) {
/*     */       Set<String> result = (Set)null;
/*     */       byte b;
/*     */       int i;
/*     */       for (b = 0, i = $this$varyFields.size(); b < i; b++) {
/*     */         if (StringsKt.equals("Vary", $this$varyFields.name(b), true)) {
/*     */           String value = $this$varyFields.value(b);
/*     */           if (result == null)
/*     */             result = new TreeSet(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE)); 
/*     */           for (String varyField : StringsKt.split$default(value, new char[] { ',' }, false, 0, 6, null)) {
/*     */             String str1 = varyField;
/*     */             boolean bool = false;
/*     */             if (str1 == null)
/*     */               throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence"); 
/*     */             result.add(StringsKt.trim(str1).toString());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       if (result == null);
/*     */       return SetsKt.emptySet();
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public final Headers varyHeaders(@NotNull Response $this$varyHeaders) {
/*     */       Intrinsics.checkNotNullParameter($this$varyHeaders, "$this$varyHeaders");
/*     */       Intrinsics.checkNotNull($this$varyHeaders.networkResponse());
/*     */       Headers requestHeaders = $this$varyHeaders.networkResponse().request().headers();
/*     */       Headers responseHeaders = $this$varyHeaders.headers();
/*     */       return varyHeaders(requestHeaders, responseHeaders);
/*     */     }
/*     */     
/*     */     private final Headers varyHeaders(Headers requestHeaders, Headers responseHeaders) {
/*     */       Set<String> varyFields = varyFields(responseHeaders);
/*     */       if (varyFields.isEmpty())
/*     */         return Util.EMPTY_HEADERS; 
/*     */       Headers.Builder result = new Headers.Builder();
/*     */       byte b;
/*     */       int i;
/*     */       for (b = 0, i = requestHeaders.size(); b < i; b++) {
/*     */         String fieldName = requestHeaders.name(b);
/*     */         if (varyFields.contains(fieldName))
/*     */           result.add(fieldName, requestHeaders.value(b)); 
/*     */       } 
/*     */       return result.build();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/Cache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */