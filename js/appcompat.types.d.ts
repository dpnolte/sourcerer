/* generated @ 2018-11-09T15:28:26.173 */
declare namespace AppcompatTypes {
  interface ActionMenuViewAttributes extends LinearLayoutCompatAttributes {
  }

  interface ActionMenuViewLayoutParamsAttributes extends LinearLayoutCompatLayoutParamsAttributes {
  }

  interface AppCompatAutoCompleteTextViewAttributes extends MainTypes.AutoCompleteTextViewAttributes {
    popupBackground?: number;
  }

  interface AppCompatButtonAttributes extends MainTypes.ButtonAttributes {
  }

  interface AppCompatCheckBoxAttributes extends MainTypes.CheckBoxAttributes {
  }

  interface AppCompatCheckedTextViewAttributes extends MainTypes.CheckedTextViewAttributes {
  }

  interface AppCompatEditTextAttributes extends MainTypes.EditTextAttributes {
  }

  interface AppCompatImageButtonAttributes extends MainTypes.ImageButtonAttributes {
  }

  interface AppCompatImageViewAttributes extends MainTypes.ImageViewAttributes {
  }

  interface AppCompatMultiAutoCompleteTextViewAttributes extends MainTypes.MultiAutoCompleteTextViewAttributes {
    popupBackground?: number;
  }

  interface AppCompatRadioButtonAttributes extends MainTypes.RadioButtonAttributes {
  }

  interface AppCompatRatingBarAttributes extends MainTypes.RatingBarAttributes {
  }

  interface AppCompatSeekBarAttributes extends MainTypes.SeekBarAttributes {
  }

  interface AppCompatSpinnerAttributes extends MainTypes.SpinnerAttributes {
    Spinner_android_dropDownWidth?: number;
  }

  interface AppCompatTextViewAttributes extends MainTypes.TextViewAttributes {
  }

  interface LinearLayoutCompatAttributes extends MainTypes.ViewGroupAttributes {
    android_baselineAligned?: boolean;
    android_baselineAlignedChildIndex?: number;
    android_gravity?: number;
    android_orientation?: number;
    divider?: number;
    dividerPadding?: string;
    measureWithLargestChild?: boolean;
    showDividers?: string;
  }

  interface LinearLayoutCompatLayoutParamsAttributes extends MainTypes.ViewGroupMarginLayoutParamsAttributes {
    android_layout_gravity?: number;
    android_layout_weight?: number;
  }

  interface SearchViewAttributes extends LinearLayoutCompatAttributes {
    android_focusable?: boolean;
    android_imeOptions?: number;
    android_inputType?: number;
    android_maxWidth?: string;
    iconifiedByDefault?: boolean;
    queryHint?: string;
  }

  interface SwitchCompatAttributes extends MainTypes.CompoundButtonAttributes {
    android_textOff?: string;
    android_textOn?: string;
    android_thumb?: number;
    showText?: boolean;
    splitTrack?: boolean;
    switchMinWidth?: string;
    switchPadding?: string;
    switchTextAppearance?: string;
    thumbTextPadding?: string;
    thumbTint?: string;
    thumbTintMode?: ThumbTintModeEnum_;
    track?: string;
    trackTint?: string;
    trackTintMode?: TrackTintModeEnum_;
  }

  enum ThumbTintModeEnum_ { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  interface ToolbarAttributes extends MainTypes.ViewGroupAttributes {
    contentInsetEnd?: string;
    contentInsetEndWithActions?: string;
    contentInsetLeft?: string;
    contentInsetRight?: string;
    contentInsetStart?: string;
    contentInsetStartWithNavigation?: string;
    logo?: number;
    logoDescription?: string;
    navigationContentDescription?: string;
    navigationIcon?: string;
    popupTheme?: number;
    subtitle?: string;
    subtitleTextColor?: string;
    title?: string;
    titleMarginBottom?: string;
    titleMarginEnd?: string;
    titleMarginStart?: string;
    titleMarginTop?: string;
    titleTextColor?: string;
  }

  interface ToolbarLayoutParamsAttributes extends MainTypes.ViewGroupLayoutParamsAttributes {
  }

  enum TrackTintModeEnum_ { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }
}
