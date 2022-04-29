module MusicPlayer
{
	sequence<byte> bytes;
	sequence<string> StringSeq;
	interface Player
	{
		int GetNumberOfMusics();
		void AddMusic(int offset, bytes partiesMusique, string path, string titre, string artistes, string album);
		void AddMusicDatabase(string titre, string artistes, string album);
		void DeleteMusic(string name);
		void ModifyMusic(string musiqueAModifier, string nouveauTitre, string nouveauxArtistes, string nouvelAlbum);
		StringSeq PrintMusics(int i);
		bool Play(string music);
		string Pause();
		bool Stop();
		StringSeq SearchTitle(string title);
		StringSeq SearchArtist(string artist);
		void Avancer();
		void Reculer();
	};

	interface PlayerFactory
	{
		Player* createPlayer();
	}
}