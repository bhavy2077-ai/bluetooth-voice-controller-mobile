# 🎤 Bluetooth Voice Controller - Complete Project

**A full-stack Bluetooth voice command system with laptop client and Android mobile receiver.**

## 📱 Project Overview

This project enables you to control your Android phone using voice commands from your laptop via secure Bluetooth connection. Speak naturally, and your phone executes the commands instantly!

## 🎯 Features

✅ **Voice Recognition** - Natural language voice commands via Google Speech API
✅ **Bluetooth Communication** - Secure RFCOMM socket connection
✅ **AES-256 Encryption** - All commands encrypted end-to-end
✅ **Modern UI** - Beautiful PyQt5 desktop and Material Design mobile apps
✅ **30+ Commands** - Open apps, make calls, control settings, and more
✅ **Real-time Execution** - Commands execute instantly on your phone
✅ **Cross-Platform** - Windows, Mac, Linux laptop support + Android 9+
✅ **Permission Handling** - Proper Android permission management
✅ **Error Recovery** - Automatic reconnection and error handling

## 🗂️ Repository Structure

### Laptop App
- **Repository**: `bluetooth-voice-controller-laptop`
- **Tech**: Python 3.8+, PyQt5, SpeechRecognition
- **Main Files**:
  - `main.py` - GUI and voice listener
  - `encryption.py` - AES encryption utilities
  - `advanced_handler.py` - Command parser
  - `config.py` - Configuration settings
  - `SETUP.md` - Installation guide

### Mobile App
- **Repository**: `bluetooth-voice-controller-mobile`
- **Tech**: Android SDK, Java, Gradle
- **Main Files**:
  - `MainActivity.java` - Activity and Bluetooth server
  - `CommandExecutor.java` - Intent-based command execution
  - `SecurityUtils.java` - AES encryption/decryption
  - `activity_main.xml` - UI layout
  - `README.md` - Mobile app guide

## 🚀 Quick Start

### Laptop Setup
```bash
git clone https://github.com/bhavy2077-ai/bluetooth-voice-controller-laptop.git
cd bluetooth-voice-controller-laptop
pip install -r requirements.txt
python main.py
```

### Mobile Setup
```bash
git clone https://github.com/bhavy2077-ai/bluetooth-voice-controller-mobile.git
cd bluetooth-voice-controller-mobile
./gradlew installDebug
```

## 🎙️ Supported Commands

| Category | Commands |
|----------|----------|
| **Apps** | YouTube, Messages, Camera, Settings, Calculator |
| **Calls** | "call [number]" - Make phone calls |
| **System** | WiFi, Bluetooth, Flashlight, Volume, Brightness |
| **Screen** | Home, Lock, Screenshot |
| **More** | Maps, Email, Notes |

## 🔐 Security

- **Transport**: Bluetooth RFCOMM with encryption
- **Data**: AES-256-CBC encryption with SHA-256 key derivation
- **Authentication**: Shared password-based (changeable)
- **No External Servers**: All processing local
- **No Personal Data Sent**: Commands only

## 📋 System Requirements

### Laptop
- Python 3.8 or higher
- Bluetooth adapter (built-in or USB)
- Windows 10+, macOS 10.14+, or Linux
- 2GB RAM minimum
- Microphone

### Mobile
- Android 9.0 (API 28) or higher
- Bluetooth 4.0+
- 100MB free storage
- Required permissions: Bluetooth, Call, SMS

## 📚 Documentation

- **[Setup Guide](SETUP.md)** - Installation and first-time setup
- **[API Documentation](API.md)** - Command protocol and message formats
- **[Architecture](ARCHITECTURE.md)** - System design and data flow
- **[Contributing](CONTRIBUTING.md)** - How to contribute
- **[License](LICENSE.md)** - MIT License

## 🔧 Customization

### Change Encryption Password
Edit in both `encryption.py` (laptop) and `SecurityUtils.java` (mobile):
```python
PASSWORD = "your-new-secure-password"
```

### Add Custom Commands
1. Laptop: Add to `COMMAND_REGISTRY` in `advanced_handler.py`
2. Mobile: Add case in `executeCommand()` method
3. Implement Intent or system call

### Adjust Voice Settings
Edit `VOICE_CONFIG` in `config.py`:
```python
VOICE_CONFIG = {
    "language": "en-US",  # Change language
    "timeout": 5,         # Listen timeout
    "energy_threshold": 4000,  # Noise threshold
}
```

## 🐛 Troubleshooting

### Connection Issues
- Ensure Bluetooth is enabled on both devices
- Keep devices within 10 meters
- Try restarting Bluetooth
- Check Bluetooth address is correct

### Voice Recognition Issues
- Speak clearly and naturally
- Reduce background noise
- Check microphone is working
- Increase `energy_threshold` if too sensitive

### Command Execution Issues
- Verify app is installed on phone
- Check app permissions in Settings
- Review command log for errors
- Ensure phone is unlocked

## 📊 Project Stats

- **2 Repositories**: Laptop + Mobile
- **15+ Python Files**: Main, config, encryption, tests
- **8+ Java Files**: Activities, services, utilities
- **Complete Documentation**: Setup, API, Architecture
- **Full Security**: AES-256 encryption
- **30+ Voice Commands**: Extensible command system

## 🎓 Learning Resources

- PyQt5 GUI Development
- Bluetooth RFCOMM Communication
- Google Speech Recognition API
- Android Intent System
- AES Encryption
- JSON Protocol Design

## 💡 Future Enhancements

- [ ] Web dashboard for command history
- [ ] Cloud sync for settings
- [ ] Machine learning for command prediction
- [ ] Multi-device support
- [ ] Custom voice profiles
- [ ] Battery optimization
- [ ] iOS support

## 🤝 Contributing

Contributions are welcome! Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details.

## 📝 License

MIT License - See [LICENSE](LICENSE.md) for details.

## 👤 Author

**@bhavy2077-ai** - Full stack developer

---

## 🌟 Give it a Star!

If you find this project helpful, please give it a ⭐ on GitHub!

**Happy Voice Controlling! 🎤✨**
