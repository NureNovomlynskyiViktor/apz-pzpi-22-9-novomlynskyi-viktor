import React, { useState } from 'react';
import '../styles/LoginPage.css';
import { useTranslation } from 'react-i18next';
import LanguageSwitcher from '../components/LanguageSwitcher';
import { login, getMe } from '../services/authService';

export default function LoginPage({ onLogin }) {
  const { t } = useTranslation();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const { token } = await login(email, password);
      localStorage.setItem('token', token);
      const userData = await getMe(token);
      onLogin(userData);
    } catch (err) {
      console.error('Login failed', err);
      setError(t('login.error') || 'Помилка входу');
    }
  };

  return (
    <div className="login-page">
      <form className="login-box animated" onSubmit={handleSubmit}>
        <div className="lang-switch-wrapper">
          <LanguageSwitcher />
        </div>

        <h2>{t('login.title')}</h2>
        <input
          type="email"
          placeholder={t('login.email')}
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder={t('login.password')}
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        {error && <div className="error-msg">{error}</div>}
        <button type="submit">{t('login.submit')}</button>
      </form>
    </div>
  );
}




