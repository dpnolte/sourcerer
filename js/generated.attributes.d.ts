/* generated @ 2018-11-05T13:34:19.456 */

declare module "LayoutTypes" {
  interface AbsListViewAttributes extends AdapterViewAttributes {
    cacheColorHint?: string;
    choiceMode?: ChoiceModeEnum;
    drawSelectorOnTop?: boolean;
    fastScrollAlwaysVisible?: boolean;
    fastScrollEnabled?: boolean;
    fastScrollStyle?: string;
    listSelector?: any;
    scrollingCache?: boolean;
    smoothScrollbar?: boolean;
    stackFromBottom?: boolean;
    textFilterEnabled?: boolean;
    transcriptMode?: TranscriptModeEnum;
  }

  interface AbsListViewLayoutParamsAttributes extends ViewGroupLayoutParamsAttributes {
  }

  interface AbsSeekBarAttributes extends ProgressBarAttributes {
    SeekBar_splitTrack?: boolean;
    SeekBar_thumb?: number;
    SeekBar_thumbOffset?: string;
    SeekBar_thumbTint?: number;
    SeekBar_thumbTintMode?: number;
    SeekBar_tickMark?: number;
    SeekBar_tickMarkTint?: number;
    SeekBar_tickMarkTintMode?: number;
    thumbTint?: string;
    thumbTintMode?: ThumbTintModeEnum;
    tickMarkTint?: string;
    tickMarkTintMode?: TickMarkTintModeEnum;
  }

  interface AbsSpinnerAttributes extends AdapterViewAttributes {
  }

  interface ActionMenuViewAttributes extends LinearLayoutAttributes {
  }

  interface ActionMenuViewLayoutParamsAttributes extends LinearLayoutLayoutParamsAttributes {
  }

  interface AdapterViewAnimatorAttributes extends AdapterViewAttributes {
    animateFirstView?: boolean;
    inAnimation?: number;
    outAnimation?: number;
  }

  interface AdapterViewAttributes extends ViewGroupAttributes {
  }

  interface AdapterViewFlipperAttributes extends AdapterViewAnimatorAttributes {
    autoStart?: boolean;
    flipInterval?: number;
  }

  enum AlignmentModeEnum { 'alignBounds', 'alignMargins' }

  interface AutoCompleteTextViewAttributes extends EditTextAttributes {
    completionHint?: string;
    completionThreshold?: number;
    dropDownAnchor?: string;
    dropDownHeight?: any;
    dropDownHorizontalOffset?: number;
    dropDownVerticalOffset?: number;
    dropDownWidth?: any;
  }

  enum AutoSizeTextTypeEnum { 'none', 'uniform' }

  enum BackgroundTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  enum BreakStrategyEnum { 'simple', 'high_quality', 'balanced' }

  interface ButtonAttributes extends TextViewAttributes {
  }

  enum ButtonTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  interface CalendarViewAttributes extends FrameLayoutAttributes {
    dateTextAppearance?: string;
    firstDayOfWeek?: number;
    focusedMonthDateColor?: string;
    maxDate?: number;
    minDate?: number;
    selectedDateVerticalBar?: string;
    selectedWeekBackgroundColor?: string;
    showWeekNumber?: boolean;
    shownWeekCount?: number;
    unfocusedMonthDateColor?: string;
    weekDayTextAppearance?: string;
    weekNumberColor?: string;
    weekSeparatorLineColor?: string;
  }

  interface CheckBoxAttributes extends CompoundButtonAttributes {
  }

  enum CheckMarkTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  interface CheckedTextViewAttributes extends TextViewAttributes {
    checkMark?: string;
    checkMarkTint?: string;
    checkMarkTintMode?: CheckMarkTintModeEnum;
    checked?: boolean;
  }

  enum ChoiceModeEnum { 'none', 'singleChoice', 'multipleChoice', 'multipleChoiceModal' }

  interface ChronometerAttributes extends TextViewAttributes {
    countDown?: boolean;
    format?: string;
  }

  interface CompoundButtonAttributes extends ButtonAttributes {
    button?: string;
    buttonTint?: string;
    buttonTintMode?: ButtonTintModeEnum;
    checked?: boolean;
  }

  interface DatePickerAttributes extends FrameLayoutAttributes {
    firstDayOfWeek?: number;
  }

  enum DrawableTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  enum DrawingCacheQualityEnum { 'auto', 'low', 'high' }

  interface EditTextAttributes extends TextViewAttributes {
  }

  enum EllipsizeEnum { 'none', 'start', 'middle', 'end', 'marquee' }

  interface ExpandableListViewAttributes extends ListViewAttributes {
    childDivider?: any;
    childIndicator?: string;
    groupIndicator?: string;
  }

  enum ForegroundTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  interface FrameLayoutAttributes extends ViewGroupAttributes {
    measureAllChildren?: boolean;
  }

  interface FrameLayoutLayoutParamsAttributes extends ViewGroupMarginLayoutParamsAttributes {
    layout_gravity?: string;
  }

  interface GridLayoutAttributes extends ViewGroupAttributes {
    alignmentMode?: AlignmentModeEnum;
    columnCount?: number;
    columnOrderPreserved?: boolean;
    orientation?: OrientationEnum_;
    rowCount?: number;
    rowOrderPreserved?: boolean;
    useDefaultMargins?: boolean;
  }

  interface GridLayoutLayoutParamsAttributes extends ViewGroupMarginLayoutParamsAttributes {
  }

  interface GridViewAttributes extends AbsListViewAttributes {
    columnWidth?: string;
    gravity?: string;
    horizontalSpacing?: string;
    numColumns?: any;
    stretchMode?: StretchModeEnum;
    verticalSpacing?: string;
  }

  interface HorizontalScrollViewAttributes extends FrameLayoutAttributes {
    fillViewport?: boolean;
  }

  enum HyphenationFrequencyEnum { 'none', 'normal', 'full' }

  interface ImageButtonAttributes extends ImageViewAttributes {
  }

  interface ImageSwitcherAttributes extends ViewSwitcherAttributes {
  }

  interface ImageViewAttributes extends ViewAttributes {
    adjustViewBounds?: boolean;
    baseline?: string;
    baselineAlignBottom?: boolean;
    cropToPadding?: boolean;
    drawableAlpha?: number;
    maxHeight?: string;
    maxWidth?: string;
    scaleType?: ScaleTypeEnum;
    src?: any;
    tint?: string;
    tintMode?: number;
  }

  enum IndeterminateTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  enum JustificationModeEnum { 'none', 'inter_word' }

  enum LayerTypeEnum { 'none', 'software', 'hardware' }

  enum LayoutDirectionEnum { 'ltr', 'rtl', 'inherit', 'locale' }

  enum LayoutModeEnum { 'clipBounds', 'opticalBounds' }

  interface LinearLayoutAttributes extends ViewGroupAttributes {
    baselineAligned?: boolean;
    baselineAlignedChildIndex?: number;
    divider?: number;
    dividerPadding?: string;
    gravity?: string;
    measureWithLargestChild?: boolean;
    orientation?: OrientationEnum;
    showDividers?: string;
  }

  interface LinearLayoutLayoutParamsAttributes extends ViewGroupMarginLayoutParamsAttributes {
    layout_gravity?: any;
    layout_weight?: number;
  }

  interface ListViewAttributes extends AbsListViewAttributes {
    divider?: any;
    dividerHeight?: string;
    footerDividersEnabled?: boolean;
    headerDividersEnabled?: boolean;
    overScrollFooter?: any;
    overScrollHeader?: any;
  }

  interface MediaControllerAttributes extends FrameLayoutAttributes {
  }

  interface MultiAutoCompleteTextViewAttributes extends AutoCompleteTextViewAttributes {
  }

  interface NumberPickerAttributes extends LinearLayoutAttributes {
    internalMinHeight?: string;
    internalMinWidth?: string;
  }

  enum OrientationEnum { 'horizontal', 'vertical' }

  enum OrientationEnum_ { 'horizontal', 'vertical' }

  enum OverScrollModeEnum { 'always', 'ifContentScrolls', 'never' }

  enum ProgressBackgroundTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  interface ProgressBarAttributes extends ViewAttributes {
    indeterminate?: boolean;
    indeterminateDrawable?: string;
    indeterminateTint?: string;
    indeterminateTintMode?: IndeterminateTintModeEnum;
    interpolator?: string;
    max?: number;
    min?: number;
    progress?: number;
    progressBackgroundTint?: string;
    progressBackgroundTintMode?: ProgressBackgroundTintModeEnum;
    progressDrawable?: string;
    progressTint?: string;
    progressTintMode?: ProgressTintModeEnum;
    secondaryProgress?: number;
    secondaryProgressTint?: string;
    secondaryProgressTintMode?: SecondaryProgressTintModeEnum;
  }

  enum ProgressTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  interface QuickContactBadgeAttributes extends ImageViewAttributes {
  }

  interface RadioButtonAttributes extends CompoundButtonAttributes {
  }

  interface RadioGroupAttributes extends LinearLayoutAttributes {
  }

  interface RadioGroupLayoutParamsAttributes extends LinearLayoutLayoutParamsAttributes {
  }

  interface RatingBarAttributes extends AbsSeekBarAttributes {
    isIndicator?: boolean;
    numStars?: number;
    rating?: number;
    stepSize?: number;
  }

  interface RelativeLayoutAttributes extends ViewGroupAttributes {
    gravity?: string;
    ignoreGravity?: string;
  }

  interface RelativeLayoutLayoutParamsAttributes extends ViewGroupMarginLayoutParamsAttributes {
  }

  enum ScaleTypeEnum { 'matrix', 'fitXY', 'fitStart', 'fitCenter', 'fitEnd', 'center', 'centerCrop', 'centerInside' }

  interface ScrollViewAttributes extends FrameLayoutAttributes {
    fillViewport?: boolean;
  }

  enum ScrollbarStyleEnum { 'insideOverlay', 'insideInset', 'outsideOverlay', 'outsideInset' }

  interface SearchViewAttributes extends LinearLayoutAttributes {
    iconifiedByDefault?: boolean;
    imeOptions?: number;
    inputType?: number;
    maxWidth?: string;
    queryHint?: string;
  }

  enum SecondaryProgressTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  interface SeekBarAttributes extends AbsSeekBarAttributes {
  }

  interface SpaceAttributes extends ViewAttributes {
  }

  interface SpinnerAttributes extends AbsSpinnerAttributes {
    dropDownWidth?: number;
    gravity?: string;
    popupBackground?: number;
  }

  interface StackViewAttributes extends AdapterViewAnimatorAttributes {
  }

  enum StretchModeEnum { 'none', 'spacingWidth', 'columnWidth', 'spacingWidthUniform' }

  interface SurfaceViewAttributes extends ViewAttributes {
  }

  interface SwitchAttributes extends CompoundButtonAttributes {
    showText?: boolean;
    splitTrack?: boolean;
    switchMinWidth?: string;
    switchPadding?: string;
    switchTextAppearance?: string;
    textOff?: string;
    textOn?: string;
    thumb?: number;
    thumbTextPadding?: string;
    thumbTint?: number;
    thumbTintMode?: number;
    track?: string;
    trackTint?: string;
    trackTintMode?: TrackTintModeEnum;
  }

  interface TabHostAttributes extends FrameLayoutAttributes {
  }

  interface TabWidgetAttributes extends LinearLayoutAttributes {
    tabStripEnabled?: boolean;
    tabStripLeft?: string;
    tabStripRight?: string;
  }

  interface TableLayoutAttributes extends LinearLayoutAttributes {
  }

  interface TableLayoutLayoutParamsAttributes extends LinearLayoutLayoutParamsAttributes {
  }

  interface TableRowAttributes extends LinearLayoutAttributes {
  }

  interface TableRowLayoutParamsAttributes extends LinearLayoutLayoutParamsAttributes {
    TableRow_Cell_layout_column?: number;
    TableRow_Cell_layout_span?: number;
  }

  interface TextClockAttributes extends TextViewAttributes {
    format12Hour?: string;
    format24Hour?: string;
    timeZone?: string;
  }

  interface TextSwitcherAttributes extends ViewSwitcherAttributes {
  }

  interface TextViewAttributes extends ViewAttributes {
    View_clickable?: boolean;
    View_longClickable?: boolean;
    autoLink?: string;
    autoSizeTextType?: AutoSizeTextTypeEnum;
    breakStrategy?: BreakStrategyEnum;
    cursorVisible?: boolean;
    drawableBottom?: any;
    drawableLeft?: any;
    drawablePadding?: string;
    drawableRight?: any;
    drawableTint?: string;
    drawableTintMode?: DrawableTintModeEnum;
    drawableTop?: any;
    editorExtras?: string;
    elegantTextHeight?: boolean;
    ellipsize?: EllipsizeEnum;
    ems?: number;
    enabled?: boolean;
    fallbackLineSpacing?: boolean;
    firstBaselineToTopHeight?: string;
    fontFeatureSettings?: string;
    freezesText?: boolean;
    gravity?: string;
    height?: string;
    hint?: string;
    hyphenationFrequency?: HyphenationFrequencyEnum;
    imeOptions?: string;
    includeFontPadding?: boolean;
    inputType?: string;
    justificationMode?: JustificationModeEnum;
    lastBaselineToBottomHeight?: string;
    letterSpacing?: number;
    lineHeight?: string;
    lines?: number;
    linksClickable?: boolean;
    marqueeRepeatLimit?: any;
    maxEms?: number;
    maxHeight?: string;
    maxLines?: number;
    maxWidth?: string;
    minEms?: number;
    minLines?: number;
    privateImeOptions?: string;
    shadowColor?: string;
    shadowDx?: number;
    shadowDy?: number;
    shadowRadius?: number;
    text?: string;
    textAllCaps?: boolean;
    textColor?: any;
    textColorHighlight?: string;
    textColorHint?: any;
    textColorLink?: any;
    textIsSelectable?: boolean;
    textScaleX?: number;
    textSize?: string;
    width?: string;
  }

  interface TextureViewAttributes extends ViewAttributes {
  }

  enum ThumbTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  enum TickMarkTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  interface TimePickerAttributes extends FrameLayoutAttributes {
  }

  interface ToggleButtonAttributes extends CompoundButtonAttributes {
    textOff?: string;
    textOn?: string;
  }

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
    titleMargin?: string;
    titleMarginBottom?: string;
    titleMarginEnd?: string;
    titleMarginStart?: string;
    titleMarginTop?: string;
    titleTextColor?: string;
  }

  interface ToolbarLayoutParamsAttributes extends ViewGroupLayoutParamsAttributes {
  }

  enum TrackTintModeEnum { 'src_over', 'src_in', 'src_atop', 'multiply', 'screen', 'add' }

  enum TranscriptModeEnum { 'disabled', 'normal', 'alwaysScroll' }

  enum VerticalScrollbarPositionEnum { 'defaultPosition', 'left', 'right' }

  interface VideoViewAttributes extends SurfaceViewAttributes {
  }

  interface ViewAnimatorAttributes extends FrameLayoutAttributes {
    FrameLayout_measureAllChildren?: boolean;
    animateFirstView?: boolean;
    inAnimation?: string;
    outAnimation?: string;
  }

  interface ViewAttributes {
    accessibilityHeading?: boolean;
    accessibilityLiveRegion?: any;
    accessibilityPaneTitle?: string;
    accessibilityTraversalAfter?: number;
    accessibilityTraversalBefore?: number;
    alpha?: number;
    autofillHints?: any;
    background?: any;
    backgroundTint?: string;
    backgroundTintMode?: BackgroundTintModeEnum;
    clickable?: boolean;
    contentDescription?: string;
    contextClickable?: boolean;
    defaultFocusHighlightEnabled?: boolean;
    drawingCacheQuality?: DrawingCacheQualityEnum;
    elevation?: string;
    fadeScrollbars?: boolean;
    filterTouchesWhenObscured?: boolean;
    fitsSystemWindows?: boolean;
    focusable?: any;
    focusableInTouchMode?: boolean;
    focusedByDefault?: boolean;
    forceHasOverlappingRendering?: boolean;
    foreground?: any;
    foregroundGravity?: string;
    foregroundTint?: string;
    foregroundTintMode?: ForegroundTintModeEnum;
    hapticFeedbackEnabled?: boolean;
    id?: string;
    importantForAccessibility?: any;
    importantForAutofill?: string;
    isScrollContainer?: boolean;
    keepScreenOn?: boolean;
    keyboardNavigationCluster?: boolean;
    labelFor?: string;
    layerType?: LayerTypeEnum;
    layoutDirection?: LayoutDirectionEnum;
    longClickable?: boolean;
    minHeight?: string;
    minWidth?: string;
    nestedScrollingEnabled?: boolean;
    nextClusterForward?: string;
    nextFocusDown?: string;
    nextFocusForward?: string;
    nextFocusLeft?: string;
    nextFocusRight?: string;
    nextFocusUp?: string;
    outlineAmbientShadowColor?: string;
    outlineSpotShadowColor?: string;
    overScrollMode?: OverScrollModeEnum;
    paddingBottom?: string;
    paddingEnd?: string;
    paddingLeft?: string;
    paddingRight?: string;
    paddingStart?: string;
    paddingTop?: string;
    rotation?: number;
    rotationX?: number;
    rotationY?: number;
    saveEnabled?: boolean;
    scaleX?: number;
    scaleY?: number;
    screenReaderFocusable?: boolean;
    scrollIndicators?: string;
    scrollX?: string;
    scrollY?: string;
    scrollbarDefaultDelayBeforeFade?: number;
    scrollbarFadeDuration?: number;
    scrollbarSize?: string;
    scrollbarStyle?: ScrollbarStyleEnum;
    soundEffectsEnabled?: boolean;
    tag?: string;
    textAlignment?: any;
    textDirection?: any;
    tooltipText?: string;
    transformPivotX?: string;
    transformPivotY?: string;
    transitionName?: string;
    translationX?: string;
    translationY?: string;
    translationZ?: string;
    verticalScrollbarPosition?: VerticalScrollbarPositionEnum;
    visibility?: VisibilityEnum;
  }

  interface ViewFlipperAttributes extends ViewAnimatorAttributes {
    autoStart?: boolean;
    flipInterval?: number;
  }

  interface ViewGroupAttributes extends ViewAttributes {
    addStatesFromChildren?: boolean;
    alwaysDrawnWithCache?: boolean;
    animateLayoutChanges?: boolean;
    animationCache?: boolean;
    clipChildren?: boolean;
    clipToPadding?: boolean;
    layoutMode?: LayoutModeEnum;
    persistentDrawingCache?: string;
    splitMotionEvents?: boolean;
    touchscreenBlocksFocus?: boolean;
    transitionGroup?: boolean;
  }

  interface ViewGroupLayoutParamsAttributes {
    layout_height?: any;
    layout_width?: any;
  }

  interface ViewGroupMarginLayoutParamsAttributes extends ViewGroupLayoutParamsAttributes {
    layout_marginBottom?: string;
    layout_marginEnd?: string;
    layout_marginLeft?: string;
    layout_marginRight?: string;
    layout_marginStart?: string;
    layout_marginTop?: string;
  }

  interface ViewStubAttributes extends ViewAttributes {
    inflatedId?: string;
    layout?: string;
  }

  interface ViewSwitcherAttributes extends ViewAnimatorAttributes {
  }

  enum VisibilityEnum { 'visible', 'invisible', 'gone' }

  interface ZoomControlsAttributes extends LinearLayoutAttributes {
  }
}
