export default (src) => {
  const sound = document.createElement('audio');
  sound.src = src;
  sound.setAttribute('preload', 'auto');
  sound.setAttribute('controls', 'none');
  sound.style.display = 'none';
  document.body.appendChild(sound);
  return sound;
};
