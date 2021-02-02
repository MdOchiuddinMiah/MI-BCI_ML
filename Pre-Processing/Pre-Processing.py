import numpy as np
import matplotlib.pyplot as plt
from scipy.signal import welch
from scipy.integrate import simps
import mne

class DataRetrive:
    edffilepath = "E:\\Python\Datasets\\test.edf"
    csvfilepath = "E:\\Python\Datasets\\test.csv"

    def __init__(self, localedfPath,localcsvPath):
        self.localedfPath = localedfPath
        self.localcsvPath=localcsvPath

    @classmethod
    def get_edffilepath(cls):
        return cls.edffilepath

    @classmethod
    def get_csvfilepath(cls):
        return cls.csvfilepath

    def get_edfdata(self):
        return mne.io.read_raw_edf(self.edffilepath)

    def get_csvdata(self):
        return pd.read_csv(self.csvfilepath)

    def save_edf_csv(self,filename,edf):
        header = ','.join(edf.ch_names)
        np.savetxt(filename, edf.get_data().T, delimiter=',', header=header)

    def getdatatype(self, data, col=None):
        return col is None and data.dtypes or data[col].dtype

class DataPreprocessing:
    sf = 128
    win_sec = 2

    def __init__(self, band):
        self.band = band

    @classmethod
    def get_sf(cls):
        return cls.sf

    @classmethod
    def get_win_sec(cls):
        return cls.win_sec

    def bandpower(self, data, sf, window_sec=None, relative=False):

        # Parameters
        # data: 1d-array; Input signal in the time-domain
        # sf: float; Sampling frequency of the data
        # band: list; Lower and upper frequencies of the band of interest
        # window_sec: float; Length of each window in seconds
        # bp : float; Absolute or relative band power

        band = np.asarray(self.band)
        low, high = band

        if window_sec is not None:
            nperseg = window_sec * sf
        else:
            nperseg = (2 / low) * sf

        freqs, psd = welch(data, sf, nperseg=nperseg)

        freq_res = freqs[1] - freqs[0]
        idx_band = np.logical_and(freqs >= low, freqs <= high)

        bp = simps(psd[idx_band], dx=freq_res)

        if relative:
            bp /= simps(psd, dx=freq_res)
        return bp

# call data retrive class
dataRetrive = DataRetrive(DataRetrive.get_edffilepath(), DataRetrive.get_csvfilepath())
erffiledata = dataRetrive.get_edfdata()
dataRetrive.save_edf_csv('test.csv',erffiledata)
csvfiledata=dataRetrive.get_csvdata()

# call Data pre processing
dataPreprocessing=DataPreprocessing([0.5, 4])
# Delta/beta ratio based on the absolute power
bp = dataPreprocessing.bandpower(csvfiledata, DataPreprocessing.get_sf(), DataPreprocessing.get_win_sec())
bp_rel = dataPreprocessing.bandpower(csvfiledata, DataPreprocessing.get_sf(), DataPreprocessing.get_win_sec(), relative=True)