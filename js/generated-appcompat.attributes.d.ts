/* generated @ 2018-11-05T13:35:34.313 */
import "./generated.attributes";

declare module "LayoutTypes" {
  interface ActionMenuViewAttributes extends LinearLayoutCompatAttributes {
  }

  interface ActionMenuViewLayoutParamsAttributes extends LinearLayoutCompatLayoutParamsAttributes {
  }

  interface AppCompatAutoCompleteTextViewAttributes extends AutoCompleteTextViewAttributes {
    popupBackground?: number;
  }

  interface AppCompatButtonAttributes extends ButtonAttributes {
  }

  interface AppCompatCheckBoxAttributes extends CheckBoxAttributes {
  }

  interface AppCompatCheckedTextViewAttributes extends CheckedTextViewAttributes {
  }

  interface AppCompatEditTextAttributes extends EditTextAttributes {
  }

  interface AppCompatImageButtonAttributes extends ImageButtonAttributes {
  }

  interface AppCompatImageViewAttributes extends ImageViewAttributes {
  }

  interface AppCompatMultiAutoCompleteTextViewAttributes extends MultiAutoCompleteTextViewAttributes {
    popupBackground?: number;
  }

  interface AppCompatRadioButtonAttributes extends RadioButtonAttributes {
  }

  interface AppCompatRatingBarAttributes extends RatingBarAttributes {
  }

  interface AppCompatSeekBarAttributes extends SeekBarAttributes {
  }

  interface AppCompatSpinnerAttributes extends SpinnerAttributes {
    Spinner_android_dropDownWidth?: number;
  }

  interface AppCompatTextViewAttributes extends TextViewAttributes {
  }

  interface LinearLayoutCompatAttributes extends ViewGroupAttributes {
    android_baselineAligned?: boolean;
    android_baselineAlignedChildIndex?: number;
    android_gravity?: number;
    android_orientation?: number;
    divider?: number;
    dividerPadding?: string;
    measureWithLargestChild?: boolean;
    showDividers?: string;
  }

  interface LinearLayoutCompatLayoutParamsAttributes extends ViewGroupMarginLayoutParamsAttributes {
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

  interface SwitchCompatAttributes extends CompoundButtonAttributes {
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

  interface ToolbarAttributes extends ViewGroupAttributes {
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

  interface ToolbarLayoutParamsAttributes extends ViewGroupLayoutParamsAttributes {
  }

  enum TrackTintModeEnum_ { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }
}
