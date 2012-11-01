module Mprodigy

  class API

    def self.instrument
      # just here to initiate monkey patching
      RhoLog::info('Mprodigy::API', 'instrument')
    end

  end

end

# monkey patching RhoApplication
module Rho

  class RhoApplication

    # patched methods
    alias_method :orig_on_activate_app, :on_activate_app
    alias_method :orig_on_deactivate_app, :on_deactivate_app

    def on_activate_app
      # RhoLog::info("mProdigy::RhoApplication", "on_activate_app")
      mprodigy_sessionBegin
      orig_on_activate_app
    end

    def on_deactivate_app
      # RhoLog::info("mProdigy::RhoApplication::on_deactivate_app", SyncEngine.logged_in)
      mprodigy_sessionEnd
      orig_on_deactivate_app
    end

    # mprodigy api methods
    def mprodigy_sessionBegin
      mprodigy_sessionEnd
      username = ''    
      if SyncEngine::logged_in == 1
        username = SyncEngine::get_user_name
      end

      @mprodigy_sessionId = Mprodigy::native_sessionBegin('store', '1.1', '', '', username)
    end
    
    def mprodigy_sessionEnd
      mprodigy_userLogout
      if @mprodigy_sessionId.to_s != ''
        Mprodigy::native_sessionEnd(@mprodigy_sessionId)
        @mprodigy_sessionId = ''
      end
    end

    def mprodigy_userLogin(username)      
      mprodigy_userLogout
      @mprodigy_userId = Mprodigy::native_userLogin(@mprodigy_sessionId, username)
    end

    def mprodigy_userLogout
       if @mprodigy_userId.to_s != ''
        Mprodigy::native_userLogout(@mprodigy_userId)
        @mprodigy_userId = ''
      end
    end

  end

end

# monkey patching sync engine to instrument login and logout
module SyncEngine

  class << self
    alias_method :orig_login, :login
    alias_method :orig_logout, :logout
  end
  
  def self.login(user, password, callback_url)
    # RhoLog::info('Mprodigy::SyncEngine', 'login')
    ::Rho.get_app.mprodigy_userLogin(user)
    orig_login(user, password, callback_url)
  end

  def self.logout
    # RhoLog::info('Mprodigy::SyncEngine::logout', 'logout')
    ::Rho.get_app.mprodigy_userLogout
    orig_logout
  end

end