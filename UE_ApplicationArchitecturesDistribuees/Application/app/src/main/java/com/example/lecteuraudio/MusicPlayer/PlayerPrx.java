//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.3
//
// <auto-generated>
//
// Generated from file `Player.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package MusicPlayer;

public interface PlayerPrx extends com.zeroc.Ice.ObjectPrx
{
    default int GetNumberOfMusics()
    {
        return GetNumberOfMusics(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default int GetNumberOfMusics(java.util.Map<String, String> context)
    {
        return _iceI_GetNumberOfMusicsAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.Integer> GetNumberOfMusicsAsync()
    {
        return _iceI_GetNumberOfMusicsAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.Integer> GetNumberOfMusicsAsync(java.util.Map<String, String> context)
    {
        return _iceI_GetNumberOfMusicsAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.Integer> _iceI_GetNumberOfMusicsAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.Integer> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "GetNumberOfMusics", null, sync, null);
        f.invoke(true, context, null, null, istr -> {
                     int ret;
                     ret = istr.readInt();
                     return ret;
                 });
        return f;
    }

    default void AddMusic(int offset, byte[] partiesMusique, String path, String titre, String artistes, String album)
    {
        AddMusic(offset, partiesMusique, path, titre, artistes, album, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void AddMusic(int offset, byte[] partiesMusique, String path, String titre, String artistes, String album, java.util.Map<String, String> context)
    {
        _iceI_AddMusicAsync(offset, partiesMusique, path, titre, artistes, album, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> AddMusicAsync(int offset, byte[] partiesMusique, String path, String titre, String artistes, String album)
    {
        return _iceI_AddMusicAsync(offset, partiesMusique, path, titre, artistes, album, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> AddMusicAsync(int offset, byte[] partiesMusique, String path, String titre, String artistes, String album, java.util.Map<String, String> context)
    {
        return _iceI_AddMusicAsync(offset, partiesMusique, path, titre, artistes, album, context, false);
    }

    /**
     * @hidden
     * @param iceP_offset -
     * @param iceP_partiesMusique -
     * @param iceP_path -
     * @param iceP_titre -
     * @param iceP_artistes -
     * @param iceP_album -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_AddMusicAsync(int iceP_offset, byte[] iceP_partiesMusique, String iceP_path, String iceP_titre, String iceP_artistes, String iceP_album, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "AddMusic", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeInt(iceP_offset);
                     ostr.writeByteSeq(iceP_partiesMusique);
                     ostr.writeString(iceP_path);
                     ostr.writeString(iceP_titre);
                     ostr.writeString(iceP_artistes);
                     ostr.writeString(iceP_album);
                 }, null);
        return f;
    }

    default void AddMusicDatabase(String titre, String artistes, String album)
    {
        AddMusicDatabase(titre, artistes, album, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void AddMusicDatabase(String titre, String artistes, String album, java.util.Map<String, String> context)
    {
        _iceI_AddMusicDatabaseAsync(titre, artistes, album, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> AddMusicDatabaseAsync(String titre, String artistes, String album)
    {
        return _iceI_AddMusicDatabaseAsync(titre, artistes, album, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> AddMusicDatabaseAsync(String titre, String artistes, String album, java.util.Map<String, String> context)
    {
        return _iceI_AddMusicDatabaseAsync(titre, artistes, album, context, false);
    }

    /**
     * @hidden
     * @param iceP_titre -
     * @param iceP_artistes -
     * @param iceP_album -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_AddMusicDatabaseAsync(String iceP_titre, String iceP_artistes, String iceP_album, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "AddMusicDatabase", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeString(iceP_titre);
                     ostr.writeString(iceP_artistes);
                     ostr.writeString(iceP_album);
                 }, null);
        return f;
    }

    default void DeleteMusic(String name)
    {
        DeleteMusic(name, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void DeleteMusic(String name, java.util.Map<String, String> context)
    {
        _iceI_DeleteMusicAsync(name, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> DeleteMusicAsync(String name)
    {
        return _iceI_DeleteMusicAsync(name, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> DeleteMusicAsync(String name, java.util.Map<String, String> context)
    {
        return _iceI_DeleteMusicAsync(name, context, false);
    }

    /**
     * @hidden
     * @param iceP_name -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_DeleteMusicAsync(String iceP_name, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "DeleteMusic", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeString(iceP_name);
                 }, null);
        return f;
    }

    default void ModifyMusic(String musiqueAModifier, String nouveauTitre, String nouveauxArtistes, String nouvelAlbum)
    {
        ModifyMusic(musiqueAModifier, nouveauTitre, nouveauxArtistes, nouvelAlbum, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void ModifyMusic(String musiqueAModifier, String nouveauTitre, String nouveauxArtistes, String nouvelAlbum, java.util.Map<String, String> context)
    {
        _iceI_ModifyMusicAsync(musiqueAModifier, nouveauTitre, nouveauxArtistes, nouvelAlbum, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> ModifyMusicAsync(String musiqueAModifier, String nouveauTitre, String nouveauxArtistes, String nouvelAlbum)
    {
        return _iceI_ModifyMusicAsync(musiqueAModifier, nouveauTitre, nouveauxArtistes, nouvelAlbum, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> ModifyMusicAsync(String musiqueAModifier, String nouveauTitre, String nouveauxArtistes, String nouvelAlbum, java.util.Map<String, String> context)
    {
        return _iceI_ModifyMusicAsync(musiqueAModifier, nouveauTitre, nouveauxArtistes, nouvelAlbum, context, false);
    }

    /**
     * @hidden
     * @param iceP_musiqueAModifier -
     * @param iceP_nouveauTitre -
     * @param iceP_nouveauxArtistes -
     * @param iceP_nouvelAlbum -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_ModifyMusicAsync(String iceP_musiqueAModifier, String iceP_nouveauTitre, String iceP_nouveauxArtistes, String iceP_nouvelAlbum, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "ModifyMusic", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeString(iceP_musiqueAModifier);
                     ostr.writeString(iceP_nouveauTitre);
                     ostr.writeString(iceP_nouveauxArtistes);
                     ostr.writeString(iceP_nouvelAlbum);
                 }, null);
        return f;
    }

    default String[] PrintMusics(int i)
    {
        return PrintMusics(i, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default String[] PrintMusics(int i, java.util.Map<String, String> context)
    {
        return _iceI_PrintMusicsAsync(i, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<String[]> PrintMusicsAsync(int i)
    {
        return _iceI_PrintMusicsAsync(i, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<String[]> PrintMusicsAsync(int i, java.util.Map<String, String> context)
    {
        return _iceI_PrintMusicsAsync(i, context, false);
    }

    /**
     * @hidden
     * @param iceP_i -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<String[]> _iceI_PrintMusicsAsync(int iceP_i, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<String[]> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "PrintMusics", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeInt(iceP_i);
                 }, istr -> {
                     String[] ret;
                     ret = istr.readStringSeq();
                     return ret;
                 });
        return f;
    }

    default boolean Play(String music)
    {
        return Play(music, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default boolean Play(String music, java.util.Map<String, String> context)
    {
        return _iceI_PlayAsync(music, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> PlayAsync(String music)
    {
        return _iceI_PlayAsync(music, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> PlayAsync(String music, java.util.Map<String, String> context)
    {
        return _iceI_PlayAsync(music, context, false);
    }

    /**
     * @hidden
     * @param iceP_music -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> _iceI_PlayAsync(String iceP_music, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "Play", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeString(iceP_music);
                 }, istr -> {
                     boolean ret;
                     ret = istr.readBool();
                     return ret;
                 });
        return f;
    }

    default String Pause()
    {
        return Pause(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default String Pause(java.util.Map<String, String> context)
    {
        return _iceI_PauseAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> PauseAsync()
    {
        return _iceI_PauseAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> PauseAsync(java.util.Map<String, String> context)
    {
        return _iceI_PauseAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.String> _iceI_PauseAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.String> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "Pause", null, sync, null);
        f.invoke(true, context, null, null, istr -> {
                     String ret;
                     ret = istr.readString();
                     return ret;
                 });
        return f;
    }

    default boolean Stop()
    {
        return Stop(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default boolean Stop(java.util.Map<String, String> context)
    {
        return _iceI_StopAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> StopAsync()
    {
        return _iceI_StopAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> StopAsync(java.util.Map<String, String> context)
    {
        return _iceI_StopAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> _iceI_StopAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "Stop", null, sync, null);
        f.invoke(true, context, null, null, istr -> {
                     boolean ret;
                     ret = istr.readBool();
                     return ret;
                 });
        return f;
    }

    default void SearchTitle(String title)
    {
        SearchTitle(title, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void SearchTitle(String title, java.util.Map<String, String> context)
    {
        _iceI_SearchTitleAsync(title, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> SearchTitleAsync(String title)
    {
        return _iceI_SearchTitleAsync(title, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> SearchTitleAsync(String title, java.util.Map<String, String> context)
    {
        return _iceI_SearchTitleAsync(title, context, false);
    }

    /**
     * @hidden
     * @param iceP_title -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_SearchTitleAsync(String iceP_title, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "SearchTitle", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeString(iceP_title);
                 }, null);
        return f;
    }

    default void searchAuthor(String author)
    {
        searchAuthor(author, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void searchAuthor(String author, java.util.Map<String, String> context)
    {
        _iceI_searchAuthorAsync(author, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> searchAuthorAsync(String author)
    {
        return _iceI_searchAuthorAsync(author, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> searchAuthorAsync(String author, java.util.Map<String, String> context)
    {
        return _iceI_searchAuthorAsync(author, context, false);
    }

    /**
     * @hidden
     * @param iceP_author -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_searchAuthorAsync(String iceP_author, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "searchAuthor", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeString(iceP_author);
                 }, null);
        return f;
    }

    default void Avancer()
    {
        Avancer(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void Avancer(java.util.Map<String, String> context)
    {
        _iceI_AvancerAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> AvancerAsync()
    {
        return _iceI_AvancerAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> AvancerAsync(java.util.Map<String, String> context)
    {
        return _iceI_AvancerAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_AvancerAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "Avancer", null, sync, null);
        f.invoke(false, context, null, null, null);
        return f;
    }

    default void Reculer(int secondes)
    {
        Reculer(secondes, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void Reculer(int secondes, java.util.Map<String, String> context)
    {
        _iceI_ReculerAsync(secondes, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> ReculerAsync(int secondes)
    {
        return _iceI_ReculerAsync(secondes, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> ReculerAsync(int secondes, java.util.Map<String, String> context)
    {
        return _iceI_ReculerAsync(secondes, context, false);
    }

    /**
     * @hidden
     * @param iceP_secondes -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_ReculerAsync(int iceP_secondes, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "Reculer", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeInt(iceP_secondes);
                 }, null);
        return f;
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static PlayerPrx checkedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, ice_staticId(), PlayerPrx.class, _PlayerPrxI.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static PlayerPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, context, ice_staticId(), PlayerPrx.class, _PlayerPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static PlayerPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, ice_staticId(), PlayerPrx.class, _PlayerPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static PlayerPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, context, ice_staticId(), PlayerPrx.class, _PlayerPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @return A proxy for this type.
     **/
    static PlayerPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, PlayerPrx.class, _PlayerPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    static PlayerPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, facet, PlayerPrx.class, _PlayerPrxI.class);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the per-proxy context.
     * @param newContext The context for the new proxy.
     * @return A proxy with the specified per-proxy context.
     **/
    @Override
    default PlayerPrx ice_context(java.util.Map<String, String> newContext)
    {
        return (PlayerPrx)_ice_context(newContext);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the adapter ID.
     * @param newAdapterId The adapter ID for the new proxy.
     * @return A proxy with the specified adapter ID.
     **/
    @Override
    default PlayerPrx ice_adapterId(String newAdapterId)
    {
        return (PlayerPrx)_ice_adapterId(newAdapterId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoints.
     * @param newEndpoints The endpoints for the new proxy.
     * @return A proxy with the specified endpoints.
     **/
    @Override
    default PlayerPrx ice_endpoints(com.zeroc.Ice.Endpoint[] newEndpoints)
    {
        return (PlayerPrx)_ice_endpoints(newEndpoints);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator cache timeout.
     * @param newTimeout The new locator cache timeout (in seconds).
     * @return A proxy with the specified locator cache timeout.
     **/
    @Override
    default PlayerPrx ice_locatorCacheTimeout(int newTimeout)
    {
        return (PlayerPrx)_ice_locatorCacheTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the invocation timeout.
     * @param newTimeout The new invocation timeout (in seconds).
     * @return A proxy with the specified invocation timeout.
     **/
    @Override
    default PlayerPrx ice_invocationTimeout(int newTimeout)
    {
        return (PlayerPrx)_ice_invocationTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for connection caching.
     * @param newCache <code>true</code> if the new proxy should cache connections; <code>false</code> otherwise.
     * @return A proxy with the specified caching policy.
     **/
    @Override
    default PlayerPrx ice_connectionCached(boolean newCache)
    {
        return (PlayerPrx)_ice_connectionCached(newCache);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoint selection policy.
     * @param newType The new endpoint selection policy.
     * @return A proxy with the specified endpoint selection policy.
     **/
    @Override
    default PlayerPrx ice_endpointSelection(com.zeroc.Ice.EndpointSelectionType newType)
    {
        return (PlayerPrx)_ice_endpointSelection(newType);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for how it selects endpoints.
     * @param b If <code>b</code> is <code>true</code>, only endpoints that use a secure transport are
     * used by the new proxy. If <code>b</code> is false, the returned proxy uses both secure and
     * insecure endpoints.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default PlayerPrx ice_secure(boolean b)
    {
        return (PlayerPrx)_ice_secure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the encoding used to marshal parameters.
     * @param e The encoding version to use to marshal request parameters.
     * @return A proxy with the specified encoding version.
     **/
    @Override
    default PlayerPrx ice_encodingVersion(com.zeroc.Ice.EncodingVersion e)
    {
        return (PlayerPrx)_ice_encodingVersion(e);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its endpoint selection policy.
     * @param b If <code>b</code> is <code>true</code>, the new proxy will use secure endpoints for invocations
     * and only use insecure endpoints if an invocation cannot be made via secure endpoints. If <code>b</code> is
     * <code>false</code>, the proxy prefers insecure endpoints to secure ones.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default PlayerPrx ice_preferSecure(boolean b)
    {
        return (PlayerPrx)_ice_preferSecure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the router.
     * @param router The router for the new proxy.
     * @return A proxy with the specified router.
     **/
    @Override
    default PlayerPrx ice_router(com.zeroc.Ice.RouterPrx router)
    {
        return (PlayerPrx)_ice_router(router);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator.
     * @param locator The locator for the new proxy.
     * @return A proxy with the specified locator.
     **/
    @Override
    default PlayerPrx ice_locator(com.zeroc.Ice.LocatorPrx locator)
    {
        return (PlayerPrx)_ice_locator(locator);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for collocation optimization.
     * @param b <code>true</code> if the new proxy enables collocation optimization; <code>false</code> otherwise.
     * @return A proxy with the specified collocation optimization.
     **/
    @Override
    default PlayerPrx ice_collocationOptimized(boolean b)
    {
        return (PlayerPrx)_ice_collocationOptimized(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses twoway invocations.
     * @return A proxy that uses twoway invocations.
     **/
    @Override
    default PlayerPrx ice_twoway()
    {
        return (PlayerPrx)_ice_twoway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses oneway invocations.
     * @return A proxy that uses oneway invocations.
     **/
    @Override
    default PlayerPrx ice_oneway()
    {
        return (PlayerPrx)_ice_oneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch oneway invocations.
     * @return A proxy that uses batch oneway invocations.
     **/
    @Override
    default PlayerPrx ice_batchOneway()
    {
        return (PlayerPrx)_ice_batchOneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses datagram invocations.
     * @return A proxy that uses datagram invocations.
     **/
    @Override
    default PlayerPrx ice_datagram()
    {
        return (PlayerPrx)_ice_datagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch datagram invocations.
     * @return A proxy that uses batch datagram invocations.
     **/
    @Override
    default PlayerPrx ice_batchDatagram()
    {
        return (PlayerPrx)_ice_batchDatagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, except for compression.
     * @param co <code>true</code> enables compression for the new proxy; <code>false</code> disables compression.
     * @return A proxy with the specified compression setting.
     **/
    @Override
    default PlayerPrx ice_compress(boolean co)
    {
        return (PlayerPrx)_ice_compress(co);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection timeout setting.
     * @param t The connection timeout for the proxy in milliseconds.
     * @return A proxy with the specified timeout.
     **/
    @Override
    default PlayerPrx ice_timeout(int t)
    {
        return (PlayerPrx)_ice_timeout(t);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection ID.
     * @param connectionId The connection ID for the new proxy. An empty string removes the connection ID.
     * @return A proxy with the specified connection ID.
     **/
    @Override
    default PlayerPrx ice_connectionId(String connectionId)
    {
        return (PlayerPrx)_ice_connectionId(connectionId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except it's a fixed proxy bound
     * the given connection.@param connection The fixed proxy connection.
     * @return A fixed proxy bound to the given connection.
     **/
    @Override
    default PlayerPrx ice_fixed(com.zeroc.Ice.Connection connection)
    {
        return (PlayerPrx)_ice_fixed(connection);
    }

    static String ice_staticId()
    {
        return "::MusicPlayer::Player";
    }
}
