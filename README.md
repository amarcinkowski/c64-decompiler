# c64-decompiler

## VICE on Ubuntu 20.04
sudo apt install vice
or
### build vice
#sudo apt install build-essential  libvte-dev libasound2-dev libgtk2.0-dev flex bison xa65 texinfo #libsdl2-dev ??

- vice
wget http://sourceforge.net/projects/vice-emu/files/releases/vice-3.4.tar.gz
tar xzf ./vice-3.4.tar.gz
cd vice-3.4
cp -r data $HOME/.vice
./configure
make
sudo make install


- from source
sudo apt-get install subversion
svn checkout https://svn.code.sf.net/p/vice-emu/code/trunk vice
cd vice
./autogen.sh
./configure
make
sudo make install
sudo ln -s /usr/local/bin/x64sc /usr/bin/x64
x64
alias x='x64 +confirmonexit'