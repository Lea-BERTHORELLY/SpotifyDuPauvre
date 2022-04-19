module MusicPlayer
{
	sequence<byte> bytes;
	interface Player
	{
		string GetMusics();
		void AddMusic(int offset, bytes partiesMusique, string path, string titre, string artistes, string album);
		//void AddMusic(int offset, bytes partiesMusique, string path);
		void DeleteMusic(string name);
		void ModifyMusic(string musiqueAModifier, string nouveauTitre, string nouveauxArtistes, string nouvelAlbum);
		//void RenameMusic(string oldName,string newName);
		//void ReplaceMusic(string oldSong,string newSong);
		void PrintMusics(string music);
		bool Play(string music);
		string Pause();
		bool Stop();
		void SearchTitle(string title);
		void searchAuthor(string author);
	}
}